package com.felix.felixapis.models.auth;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OTPModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "otp_id")
    private long otpId;

    @Column(name = "otp")
    private int otp;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @OneToOne
    @JoinColumn(nullable = false, name = "user_id", referencedColumnName = "id")
    private User userId;

    public OTPModel() {
    }

    public OTPModel(User userId, int otp) {
        this.userId = userId;
        creationDate = new Date(System.currentTimeMillis());
        this.otp = otp;
    }

    public long getOtpId() {
        return otpId;
    }

    public void setOtpId(long otpId) {
        this.otpId = otpId;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
