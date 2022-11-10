package com.felix.felixapis.services.auth;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class ResetPasswordService {

    @Autowired
    GetDetailsFromUser getDetailsFromUser;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<?> resetPassword(String email, String oldPass, String newPass) {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            System.out.println("exists");
            try {
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, oldPass));
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }

            if(Objects.equals(oldPass, newPass)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("New password can't be same as old password");
            } else {
                User user = userRepository.findUserByEmailIgnoreCase(email);
                user.setPassword(passwordEncoder.encode(newPass));
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Password changed successfully");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with the email provided");
        }


    }

}
