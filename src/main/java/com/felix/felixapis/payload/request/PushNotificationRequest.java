package com.felix.felixapis.payload.request;

public class PushNotificationRequest {
    private String title;
    private String body;
    private String token;

    public PushNotificationRequest() {
    }

    public PushNotificationRequest(String title, String body) {
        this.title = title;
        this.body = body;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

