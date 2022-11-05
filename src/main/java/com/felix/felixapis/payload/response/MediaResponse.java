package com.felix.felixapis.payload.response;

public class MediaResponse {
    private String message;


    public MediaResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


