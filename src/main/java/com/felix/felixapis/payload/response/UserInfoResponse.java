package com.felix.felixapis.payload.response;

public class UserInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public UserInfoResponse(Long id, String email, String firstName, String lastName, String role) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;

    }


    public UserInfoResponse(String firstName, String lastName,String email)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    public UserInfoResponse(String setFirstName, String setLastName, String setEmail) {
//    }

//    public UserInfoResponse(void setFirstName, void setLastName, void setEmail) {
//    }

//    public UserInfoResponse(String setFirstName, String setLastName, String setEmail) {
//    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
