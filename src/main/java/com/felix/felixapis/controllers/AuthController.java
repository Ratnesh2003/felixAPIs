package com.felix.felixapis.controllers;

import com.felix.felixapis.models.auth.EmailConfirmationModel;
import com.felix.felixapis.models.auth.OTPModel;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.payload.request.auth.ConfirmOTPRequest;
import com.felix.felixapis.payload.request.auth.LoginRequest;
import com.felix.felixapis.payload.request.auth.SignupRequest;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.auth.ConfirmationTokenRepository;
import com.felix.felixapis.repository.auth.OTPRepository;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.EmailServices;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Random;

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

    @Autowired
    OTPRepository otpRepository;

    Random random = new Random();

    @PostMapping("/api/auth/signup")
    public String registerUser(@Valid @RequestBody SignupRequest signupRequest, HttpServletRequest httpRequest) throws MessagingException {

    String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();

        if(userRepository.existsByEmailIgnoreCase(signupRequest.getEmail())) {
            UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(signupRequest.getEmail());
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

        EmailConfirmationModel emailConfirmationModel = new EmailConfirmationModel(user.getId());
        confirmationTokenRepository.save(emailConfirmationModel);

        emailServices.sendMessageWithAttachment("innitt090@gmail.com",
                user.getEmail(),
                "Email Verification Felix",
                "To verify your account, please click the following link: \n" +
                "<a href=\""+ baseURL + "/api/auth/confirm-account?token=" + emailConfirmationModel.getConfirmationToken() +
                "\"> Activate now</a>");
        return "Please check your email for verification";
    }

    @PutMapping("/api/auth/resend-verification-link")
    public String resendVerificationLink(@RequestBody LoginRequest loginRequest, HttpServletRequest httpRequest) throws MessagingException {

        String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();

        if(userRepository.existsByEmailIgnoreCase(loginRequest.getEmail())) {
            User user = userRepository.findUserByEmailIgnoreCase(loginRequest.getEmail());
            EmailConfirmationModel emailConfirmationModel = confirmationTokenRepository.findEmailConfirmationModelByUserId(user.getId());
            EmailConfirmationModel newEmailConfirmModel = new EmailConfirmationModel(user.getId());
            newEmailConfirmModel.setTokenId(emailConfirmationModel.getTokenId());
            confirmationTokenRepository.save(newEmailConfirmModel);

            emailServices.sendMessageWithAttachment("innitt090@gmail.com",
                    user.getEmail(),
                    "Email Verification Felix",
                    "To verify your account, please click the following link: \n" +
                            "<a href=\""+ baseURL + "/api/auth/confirm-account?token=" + emailConfirmationModel.getConfirmationToken() +
                            "\"> Activate now</a>");

            return "Verification link sent to the given Email";
        } else {
            return "Invalid user";
        }
    }

    @RequestMapping(value = "/api/auth/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token")String confirmationToken) {
        EmailConfirmationModel token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(new Date().getTime() > token.getCreationDate().getTime() + 5*60*1000) {
            return "Verification link expired";
        } else {
            if(token != null) {
//                User user = userRepository.findUserByEmailIgnoreCase(token.getUser().getEmail());
                User user = userRepository.findUserById(token.getUserId());
                user.setEnabled(true);
                userRepository.save(user);
                return "Account verified";
            } else {
                return "The link is invalid";
            }
        }
    }

    @PostMapping("/api/auth/login")
//    @ResponseBody
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {


        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());
            try {
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            } catch (UsernameNotFoundException ex) {
                throw new Exception("Invalid Email or Password");
            }
        String jwtCookie = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie)
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getRole()));
    }

    @PutMapping("/api/auth/forgot-password")
    public String forgotPass(@RequestBody LoginRequest loginRequest) throws MessagingException {
        if(userRepository.existsByEmailIgnoreCase(loginRequest.getEmail())) {
            User user = userRepository.findUserByEmailIgnoreCase(loginRequest.getEmail());
            OTPModel otpModel = otpRepository.findOTPModelByUserId(user.getId());
            int otp = random.nextInt(899999) + 100000;
            if(otpModel == null) {
                OTPModel otpModelNew = new OTPModel(user.getId(), otp);
                otpRepository.save(otpModelNew);

            } else {
                otpModel.setOtpId(otpModel.getOtpId());
                otpModel.setCreationDate(new Date());
                otpModel.setOtp(otp);
                otpRepository.save(otpModel);
            }
            emailServices.sendMessageWithAttachment(
                    "innitt090@gmail.com",
                    loginRequest.getEmail(), "RESET PASSWORD FELIX",
                    "OTP to reset password:  <b>"  + Integer.toString(otp) + "</b>"
            );
            return "OTP sent on the given mail";
        } else {
            return "User not found with the given email";
        }
    }
    @PostMapping("/api/auth/confirm-otp")
    public String confirmOtp(@RequestBody ConfirmOTPRequest confirmOTPRequest) {
        if(userRepository.existsByEmailIgnoreCase(confirmOTPRequest.getEmail())) {
            User user = userRepository.findUserByEmailIgnoreCase(confirmOTPRequest.getEmail());
            OTPModel otpModel = otpRepository.findOTPModelByUserId(user.getId());

            if(new Date().getTime() > otpModel.getCreationDate().getTime() + 5*60*1000) {
                return "OTP Expired";
            } else {
                int originalOTP = otpModel.getOtp();
                int providedOTP = confirmOTPRequest.getOtp();
                if(originalOTP == providedOTP) {
                    return "OTP Verified";
                } else {
                    return "Incorrect OTP";
                }
            }
        } else {
            return "User doesn't exist";
        }
    }

    @PutMapping("/api/auth/change-password")
    public String changePass(@RequestBody ConfirmOTPRequest confirmOTPRequest) {
        if(userRepository.existsByEmailIgnoreCase(confirmOTPRequest.getEmail())) {
            User user = userRepository.findUserByEmailIgnoreCase(confirmOTPRequest.getEmail());
            OTPModel otpModel = otpRepository.findOTPModelByUserId(user.getId());
            if(new Date().getTime() > otpModel.getCreationDate().getTime() + 5*60*1000) {
                return "OTP Expired";
            } else {
                int originalOTP = otpModel.getOtp();
                int providedOTP = confirmOTPRequest.getOtp();
                if(originalOTP == providedOTP) {
                    user.setPassword(passwordEncoder.encode(confirmOTPRequest.getPassword()));
                    userRepository.save(user);
                    return "Password changed successfully";
                } else {
                    return "Incorrect OTP";
                }
            }
        } else {
            return "User not found";
        }
    }

    @PutMapping("/api/auth/resend-otp")
    public String resendOTP(@RequestBody ConfirmOTPRequest confirmOTPRequest) throws MessagingException {
        if(userRepository.existsByEmailIgnoreCase(confirmOTPRequest.getEmail())) {
            User user = userRepository.findUserByEmailIgnoreCase(confirmOTPRequest.getEmail());
            OTPModel otpModel = otpRepository.findOTPModelByUserId(user.getId());
            int otp = random.nextInt(899999) + 100000;
            otpModel.setOtpId(otpModel.getOtpId());
            otpModel.setOtp(otp);
            otpModel.setCreationDate(new Date());
            otpRepository.save(otpModel);
            emailServices.sendMessageWithAttachment(
                    "innitt090@gmail.com",
                    confirmOTPRequest.getEmail(), "RESET PASSWORD FELIX",
                    "OTP to reset password:  <b>"  + Integer.toString(otp) + "</b>"
            );
            return "OTP sent on the given mail";
        } else {
            return "User doesn't exist";
        }
    }




}
