package com.felix.felixapis.payload.request;

public class NotificationRequest {
    private String targetRole;
    private String notificationTitle;
    private String notificationBody;
    private String notificationLink;

    public NotificationRequest() {
    }

    public NotificationRequest(String targetRole, String notificationTitle, String notificationBody, String notificationLink) {
        this.targetRole = targetRole;
        this.notificationTitle = notificationTitle;
        this.notificationBody = notificationBody;
        this.notificationLink = notificationLink;
    }

    public String getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(String targetRole) {
        this.targetRole = targetRole;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationBody() {
        return notificationBody;
    }

    public void setNotificationBody(String notificationBody) {
        this.notificationBody = notificationBody;
    }

    public String getNotificationLink() {
        return notificationLink;
    }

    public void setNotificationLink(String notificationLink) {
        this.notificationLink = notificationLink;
    }
}
