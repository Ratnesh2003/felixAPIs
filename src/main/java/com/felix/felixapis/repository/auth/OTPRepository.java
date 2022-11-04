package com.felix.felixapis.repository.auth;

import com.felix.felixapis.models.auth.OTPModel;
import org.springframework.data.repository.CrudRepository;

public interface OTPRepository extends CrudRepository<OTPModel, Long> {
//    int findOTPByUserId(int userId);
    OTPModel findOTPModelByUserId(long userId);
}
