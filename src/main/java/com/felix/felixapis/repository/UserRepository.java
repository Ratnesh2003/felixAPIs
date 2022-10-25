package com.felix.felixapis.repository;

import com.felix.felixapis.model.FelixUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<FelixUser, Long> {
    FelixUser findByUsername(String username);

}
