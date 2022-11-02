package com.felix.felixapis.payload.request.auth;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ConfirmOTPRequest implements Serializable {

    @NotNull
    private String email;

    @NotNull
    private int otp;

    @NotNull
    @Size(min = 8, max = 30)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain a lowercase character, an uppercase character, a special character and a number")
    private String password;

    public ConfirmOTPRequest() {
    }

    public ConfirmOTPRequest(String email, int otp, String password) {
        this.email = email;
        this.otp = otp;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
