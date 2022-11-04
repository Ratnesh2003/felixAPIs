package com.felix.felixapis.repository.auth;

import com.felix.felixapis.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String findByEmail(String email);
    User findUserByEmailIgnoreCase(String email);

    User findUserById(long id);
    Boolean existsByEmailIgnoreCase(String email);

//    Long findIdByEmail(String email);
}
