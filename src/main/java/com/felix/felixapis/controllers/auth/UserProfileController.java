package com.felix.felixapis.controllers.auth;

import com.felix.felixapis.payload.request.ChangeProfileRequest;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.services.impl.ProfileServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserProfileController {

    final
    ProfileServicesImpl profileServicesImpl;
    private final JwtUtil jwtUtil;

    public UserProfileController(JwtUtil jwtUtil, ProfileServicesImpl profileServicesImpl) {
        this.jwtUtil = jwtUtil;
        this.profileServicesImpl = profileServicesImpl;
    }

    @GetMapping("/api/profilePage")
    public UserInfoResponse userProfile(HttpServletRequest httpRequest){
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String email = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return profileServicesImpl.getUserProfile(email);
    }
    @PutMapping("/api/change-profile")
    public ResponseEntity<?> changeProfile(@RequestBody ChangeProfileRequest changeProfileRequest, HttpServletRequest httpRequest){
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String oldEmail = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        return profileServicesImpl.changeUserProfile(changeProfileRequest.getNewFirstName(),changeProfileRequest.getNewLastName(),oldEmail);

    }
    @GetMapping("/api/change-profile/send-email-verification")
    public ResponseEntity<?> changeEmail(@RequestParam("newEmail") String newEmail,HttpServletRequest httpRequest) throws MessagingException {

        return profileServicesImpl.changeUserEmail(newEmail,httpRequest);
    }
    @RequestMapping(value="/api/change-profile/save-new-email", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<?> saveNewEmail(@RequestParam("token") String confirmationToken, @RequestParam("email")String newEmail ){
        return profileServicesImpl.saveNewEmail(newEmail, confirmationToken);
//        String requestTokenHeader = httpRequest.getHeader("Authorization");
//        String oldEmail = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
//        return profileServices.saveNewEmail(newEmail,oldEmail,confirmationToken);
    }
}
