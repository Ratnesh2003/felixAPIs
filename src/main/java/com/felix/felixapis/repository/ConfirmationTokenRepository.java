package com.felix.felixapis.repository;


import com.felix.felixapis.models.EmailConfirmationModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<EmailConfirmationModel, String> {
    EmailConfirmationModel findByConfirmationToken(String confirmationToken);
}
