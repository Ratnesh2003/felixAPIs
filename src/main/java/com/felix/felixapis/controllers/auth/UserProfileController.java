package com.felix.felixapis.controllers.auth;

import com.felix.felixapis.payload.request.ChangeProfileRequest;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.auth.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
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
    @PutMapping("/api/change-profile")
    public ResponseEntity<?> changeProfile(@RequestBody ChangeProfileRequest changeProfileRequest, HttpServletRequest httpRequest){
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String oldEmail = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return profileServices.changeUserProfile(changeProfileRequest.getNewFirstName(),changeProfileRequest.getNewLastName(),oldEmail);

    }
    @GetMapping("/api/change-profile/send-email-verification")
    public ResponseEntity<?> changeEmail(@RequestParam("newEmail") String newEmail,HttpServletRequest httpRequest) throws MessagingException {

        return profileServices.changeUserEmail(newEmail,httpRequest);
    }
    @RequestMapping(value="/api/change-profile/save-new-email", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<?> saveNewEmail(@RequestParam("token")String confirmationToken,@RequestParam("email")String newEmail , HttpServletRequest httpRequest){
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String oldEmail = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return profileServices.saveNewEmail(newEmail,oldEmail,confirmationToken);
    }
}
