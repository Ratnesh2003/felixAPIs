package com.felix.felixapis.controllers;

import com.felix.felixapis.models.auth.User;
import com.felix.felixapis.payload.request.auth.ConfirmOTPRequest;
import com.felix.felixapis.repository.auth.UserRepository;
import com.felix.felixapis.services.admin.NewAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    NewAdminService newAdminService;

    @PutMapping("/api/admin/make-new-admin")
    public ResponseEntity<?> makeNewAdmin(@RequestBody ConfirmOTPRequest emailObject, HttpServletRequest httpRequest) {
        return newAdminService.makeNewAdmin(emailObject.getEmail(), httpRequest);
    }

}
