package com.felix.felixapis.controllers;

import com.felix.felixapis.models.Notification;
import com.felix.felixapis.payload.request.PushNotificationRequest;
import com.felix.felixapis.services.PushNotificationService;
import com.felix.felixapis.services.admin.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {

    final
    NotificationService notificationService;

    final PushNotificationService pushNotificationService;

    public NotificationController(NotificationService notificationService, PushNotificationService pushNotificationService) {
        this.notificationService = notificationService;
        this.pushNotificationService = pushNotificationService;
    }



    @GetMapping("/api/notifications")
    public ResponseEntity<List<Notification>> notificationPage(HttpServletRequest httpRequest) {
        return notificationService.getAllNotifications(httpRequest);
    }

//    @PostMapping("/api/push-notificaiton/token")
//    public ResponseEntity<?> sendTokenNotification(@RequestBody PushNotificationRequest request) {
//
//    }

    @PostMapping("/api/admin/send-push-notifications")
    public ResponseEntity<?> sendDataNotification(@RequestBody PushNotificationRequest request) throws ExecutionException, InterruptedException {
        pushNotificationService.sendPushNotification(request);
        return ResponseEntity.status(HttpStatus.OK).body("Notification sent...");

    }
}
