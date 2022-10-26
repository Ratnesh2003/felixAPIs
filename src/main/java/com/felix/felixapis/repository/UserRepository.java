package com.felix.felixapis.repository;

import com.felix.felixapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
@RestController
@RequestMapping("/api/auth")
public interface UserRepository extends JpaRepository<User, Long> {
    String findByEmail(String email);
    User findUserByEmail(String email);
    Boolean existsByEmail(String email);
}
