package com.felix.felixapis.repository;

import com.felix.felixapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String findByEmail(String email);
    User findUserByEmailIgnoreCase(String email);
    Boolean existsByEmailIgnoreCase(String email);
}
