package com.felix.felixapis.controllers;

import com.felix.felixapis.models.User;
import com.felix.felixapis.payload.request.LoginRequest;
import com.felix.felixapis.payload.request.SignupRequest;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

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

    @PostMapping("/signup")
    public String registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if(userRepository.existsByEmail(signupRequest.getEmail())) {
            return "Email already in use";
        }
        User user = new User(
                signupRequest.getEmail(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                passwordEncoder.encode(signupRequest.getPassword())
        );
        userRepository.save(user);
        return "User registered successfully.";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
            throw new Exception("Invalid Email or Password");
        }

        UserDetailsImpl userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getEmail());

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
