package com.felix.felixapis.services.admin;

import com.felix.felixapis.payload.request.NotificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class NotificationService {

    public ResponseEntity<?> sendNotification(NotificationRequest notificationRequest, HttpServletRequest httpRequest) {
        return ResponseEntity.status(HttpStatus.OK).body("Notification sent successfully");
    }

}
