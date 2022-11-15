package com.felix.felixapis.controllers;

import com.felix.felixapis.models.Notification;
import com.felix.felixapis.services.admin.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class NotificationController {

    final
    NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> notificationPage(HttpServletRequest httpRequest) {
        return notificationService.getAllNotifications(httpRequest);

    }
}
