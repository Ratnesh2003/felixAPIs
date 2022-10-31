package com.felix.felixapis.repository.auth;


import com.felix.felixapis.models.auth.EmailConfirmationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfirmationTokenRepository extends CrudRepository<EmailConfirmationModel, String> {
    EmailConfirmationModel findByConfirmationToken(String confirmationToken);
}
