package com.felix.felixapis.controllers.auth;

import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.auth.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserProfileController {

    @Autowired
    ProfileServices profileServices;
    private final JwtUtil jwtUtil;

    public UserProfileController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/api/profilePage")
    public UserInfoResponse userProfile(HttpServletRequest httpRequest){
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return profileServices.getUserProfile(email);

    }
}