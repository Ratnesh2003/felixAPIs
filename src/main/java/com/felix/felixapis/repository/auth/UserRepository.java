package com.felix.felixapis.repository.auth;

import com.felix.felixapis.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findUserByEmailIgnoreCase(String email);

    User findUserById(long id);
    Boolean existsByEmailIgnoreCase(String email);

}
