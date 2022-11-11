package com.felix.felixapis.services.admin;

import com.felix.felixapis.helper.GetDetailsFromUser;
import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class NewAdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GetDetailsFromUser getDetailsFromUser;

    public Boolean checkAdmin(HttpServletRequest httpServletRequest) {
        long userId = getDetailsFromUser.getUserId(httpServletRequest);
        User user = userRepository.findUserById(userId);
        return Objects.equals(user.getRole(), "ADMIN");
    }

    public ResponseEntity<?> makeNewAdmin(String email, HttpServletRequest httpRequest) {
        if(checkAdmin(httpRequest)) {
            User user = userRepository.findUserByEmailIgnoreCase(email);
            if (Objects.equals(user.getRole(), "ADMIN")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(user.getFirstName() + " " + user.getLastName() + " is already an ADMIN.");
            } else {
                user.setRole("ADMIN");
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.OK).body(user.getFirstName() + " " + user.getLastName() + " is now ADMIN.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You must be an ADMIN to perform this action.");
        }
    }
}
