package com.felix.felixapis.services.auth;

import com.felix.felixapis.models.auth.EmailConfirmationModel;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.payload.response.UserInfoResponse;
import com.felix.felixapis.repository.auth.ConfirmationTokenRepository;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.security.jwt.JwtUtil;
import com.felix.felixapis.security.services.EmailServices;
import com.felix.felixapis.security.services.UserDetailsImpl;
import com.felix.felixapis.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Service
public class ProfileServices {
    @Autowired
    UserRepository userRepository;
    private JwtUtil jwtUtil;

    public void UserProfileController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    EmailServices emailServices;


    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    public ProfileServices(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

//private final EmailConfirmationModel emailConfirmationModel;
//    public ProfileServices(EmailConfirmationModel emailConfirmationModel) {
//        this.emailConfirmationModel = emailConfirmationModel;
//
//    }

    public UserInfoResponse getUserProfile(String email) {
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(email);
        return new UserInfoResponse(userDetails.getId(),
                userDetails.getEmail(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getRole());
    }

    public ResponseEntity<?> changeUserProfile(String newFirstName, String newLastName,String oldEmail) {
        User userDetails = userRepository.findUserByEmailIgnoreCase(oldEmail);
        userDetails.setFirstName(newFirstName);
        userDetails.setLastName(newLastName);

        userRepository.save(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body("User Details Updated");


    }
    public ResponseEntity<?> changeUserEmail(String newEmail, HttpServletRequest httpRequest) throws MessagingException {
        String requestTokenHeader = httpRequest.getHeader("Authorization");
        String oldEmail = jwtUtil.getEmailFromToken(requestTokenHeader.substring(7));
        User userDetails = userRepository.findUserByEmailIgnoreCase(oldEmail);
        EmailConfirmationModel emailConfirmationModel = new EmailConfirmationModel(userDetails.getId());
        confirmationTokenRepository.save(emailConfirmationModel);
        String baseURL = ServletUriComponentsBuilder.fromRequestUri(httpRequest).replacePath(null).build().toUriString();

        emailServices.sendMessageWithAttachment("innitt090@gmail.com",
                    newEmail,
                    "Email Verification Felix",
                    "Hey! Click " + "<a href=\"" + baseURL + "/api/change-profile/save-new-email?token=" + emailConfirmationModel.getConfirmationToken()+
                            "&email="+ newEmail+
                            "\">here</a>" + " to verify your new email");
        return ResponseEntity.status(HttpStatus.OK).body("Verification link sent to the given Email");
    }
    public ResponseEntity<?> saveNewEmail(String newEmail, String confirmationToken){
        EmailConfirmationModel token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token==null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("The link is invalid");
        }
        else{
            User userDetails = userRepository.findUserById(token.getUserId());
            userDetails.setEmail(newEmail);
            userRepository.save(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body("Email Updated");
        }

    }
}