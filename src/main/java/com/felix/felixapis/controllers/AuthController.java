package com.felix.felixapis.controllers;

import com.felix.felixapis.models.EmailConfirmationModel;
import com.felix.felixapis.models.User;
import com.felix.felixapis.payload.request.LoginRequest;
import com.felix.felixapis.payload.request.SignupRequest;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.ConfirmationTokenRepository;
import com.felix.felixapis.repository.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.EmailServices;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailServices emailServices;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

//    @Autowired
//    EmailServices emailServices;

    @PostMapping("/api/auth/signup")
    public String registerUser(@Valid @RequestBody SignupRequest signupRequest) throws MessagingException {







        if(userRepository.existsByEmailIgnoreCase(signupRequest.getEmail())) {
            UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(signupRequest.getEmail());
            System.out.println(userDetails.isEnabled());
            if(!userDetails.isEnabled()) {
                return "Please verify your email first.";
            } else {
                return "Email already in use";
            }

        }


        User user = new User(
                signupRequest.getEmail(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                passwordEncoder.encode(signupRequest.getPassword())
        );
        userRepository.save(user);

        EmailConfirmationModel emailConfirmationModel = new EmailConfirmationModel(user);
        confirmationTokenRepository.save(emailConfirmationModel);

        emailServices.sendMessageWithAttachment("innitt090@gmail.com",
                user.getEmail(),
                "Email Verification Felix",
                "To verify your account, please click the following link: \n" +
                "<a href=\"https://felixapis.herokuapp.com/api/auth/confirm-account?token=" + emailConfirmationModel.getConfirmationToken() +
                "\"> Activate now</a>");


        return "Please check your email for verification";
    }

    @RequestMapping(value = "/api/auth/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken) {
        EmailConfirmationModel token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            User user = userRepository.findUserByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return "Account verified";
        } else {
            return "The link is invalid";
        }
    }

    @PostMapping("/api/auth/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {


        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
            try {
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            } catch (UsernameNotFoundException ex) {
//                ex.printStackTrace();
                throw new Exception("Invalid Email or Password");
            }




        String jwtCookie = jwtUtil.generateToken(userDetails);


//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        System.out.println(userDetails);


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie)
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getRole()));
    }




}
