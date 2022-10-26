package com.felix.felixapis.payload.response;

public class UserInfoResponse {
    private Long id;
    private String email;
    private String role;

    public UserInfoResponse(Long id, String email) {
        this.id = id;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
