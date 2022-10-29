package com.felix.felixapis.payload.request;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public LoginRequest() {

    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
