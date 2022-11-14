package com.felix.felixapis.services.auth;

import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> changeUserProfile(String newFirstName, String newLastName, String newEmail,  String oldEmail){
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(oldEmail);
       userDetails.setFirstName(newFirstName);
       userDetails.setLastName(newLastName);
       userDetails.setEmail(newEmail);
//           UserInfoResponse updateDetails = new UserInfoResponse(userDetails.getFirstName(),
//                   userDetails.getLastName(),userDetails.getEmail());

         userRepository.save(userDetails);
//
        return ResponseEntity.status(HttpStatus.OK).body("User Details Updated");

    }
}
