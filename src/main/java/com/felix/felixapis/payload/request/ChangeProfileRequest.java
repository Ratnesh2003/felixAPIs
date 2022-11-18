package com.felix.felixapis.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ChangeProfileRequest {
    @NotBlank
    private String newFirstName;
    @NotBlank
    private String newLastName;
    @NotBlank
    @Email
    private String newEmail;

    public ChangeProfileRequest() {
    }

    public ChangeProfileRequest(String newFirstName,String newLastName,String newEmail) {
        this.newFirstName = newFirstName;
        this.newLastName = newLastName;
        this.newEmail = newEmail;
    }


    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String getNewFirstName() {
        return newFirstName;
    }

    public void setNewFirstName(String newFirstName) {
        this.newFirstName = newFirstName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
