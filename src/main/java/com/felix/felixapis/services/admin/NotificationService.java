package com.felix.felixapis.services.admin;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.Notification;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.payload.request.NotificationRequest;
import com.felix.felixapis.repository.NotificationRepository;
import com.felix.felixapis.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    final
    GetDetailsFromUser getDetailsFromUser;

    final
    NotificationRepository notificationRepository;

    final
    NewAdminService newAdminService;

    final UserRepository userRepository;

    public NotificationService(GetDetailsFromUser getDetailsFromUser, NotificationRepository notificationRepository, NewAdminService newAdminService, UserRepository userRepository) {
        this.getDetailsFromUser = getDetailsFromUser;
        this.notificationRepository = notificationRepository;
        this.newAdminService = newAdminService;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> sendNotification(NotificationRequest notificationRequest, HttpServletRequest httpRequest) {

        if (!newAdminService.checkAdmin(httpRequest)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to perform this action");
        } else {
            System.out.println(notificationRequest.getTargetRole());
            System.out.println("ABove line");
            long userId = getDetailsFromUser.getUserId(httpRequest);
            Notification notification = new Notification(
                    userId,
                    notificationRequest.getTargetRole(),
                    notificationRequest.getNotificationTitle(),
                    notificationRequest.getNotificationBody(),
                    notificationRequest.getNotificationLink(),
                    new Date()
            );
            notificationRepository.save(notification);
            return ResponseEntity.status(HttpStatus.OK).body("Notification sent successfully");
        }
    }

    public ResponseEntity<List<Notification>> getAllNotifications(HttpServletRequest httpRequest) {
        long userId = getDetailsFromUser.getUserId(httpRequest);
        User user = userRepository.findUserById(userId);
        String role = user.getRole();
        return ResponseEntity.status(HttpStatus.OK).body(notificationRepository.findByTargetRole(role));
    }

}
