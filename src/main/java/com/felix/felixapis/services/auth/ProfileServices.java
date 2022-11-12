package com.felix.felixapis.services.auth;

import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProfileServices {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    public UserInfoResponse getUserProfile(String email){
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(email);
        return new UserInfoResponse(userDetails.getId(),
                userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getRole());
    }
}
