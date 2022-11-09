package com.felix.felixapis.helper;

import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class GetDetailsFromUser {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    public long getUserId(HttpServletRequest httpRequest) {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return userRepository.findUserByEmailIgnoreCase(email).getId();
    }

}
