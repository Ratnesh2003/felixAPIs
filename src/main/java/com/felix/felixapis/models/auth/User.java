package com.felix.felixapis.models.auth;

//<<<<<<< HEAD
//import com.felix.felixapis.models.WatchedHistory;
//=======
import com.felix.felixapis.models.movie.WatchedHistory;
//>>>>>>> 9a5e305bd207e9d4338ebbd766dd79a36d5ef4b9

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="felix_users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
//    @Size(max = 50)
//    @Email
    private String email;
//
//    @NotNull
//    @Size(max = 30)
    private String firstName;

//    @NotNull
//    @Size(max = 30)
    private String lastName;

//    @NotNull
//    @Size(max = 100)
    private String password;

    private String role = "USER";

    private boolean isEnabled = false;

    public User() {
    }

    public User(Long id,String email, String firstName, String lastName) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
@OneToMany(targetEntity = WatchedHistory.class,cascade = CascadeType.ALL)
@JoinColumn(name="movie_id",referencedColumnName = "id")
    private List<WatchedHistory> watched;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
