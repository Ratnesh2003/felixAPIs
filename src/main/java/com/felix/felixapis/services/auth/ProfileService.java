package com.felix.felixapis.services.auth;

import com.felix.felixapis.payload.response.UserInfoResponse;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

public interface ProfileService {
    UserInfoResponse getUserProfile(String email);
    ResponseEntity<?> changeUserProfile(String newFirstName, String newLastName, String oldEmail);
    ResponseEntity<?> changeUserEmail(String newEmail, HttpServletRequest httpRequest) throws MessagingException;
    ResponseEntity<?> saveNewEmail(String newEmail, String confirmationToken);
}
