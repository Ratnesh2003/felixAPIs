package com.felix.felixapis.models.auth;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "emailConfirmationToken")
public class EmailConfirmationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="token_id")
    private long tokenId;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
    private Long userId;

    public EmailConfirmationModel() {
    }

    public EmailConfirmationModel(long userId) {
        this.userId = userId;
        creationDate = new Date();
        confirmationToken = UUID.randomUUID().toString();
    }

    public long getTokenId() {
        return tokenId;
    }

    public void setTokenId(long tokenId) {
        this.tokenId = tokenId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
