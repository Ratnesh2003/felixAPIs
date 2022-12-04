package com.felix.felixapis.services.admin;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface NewAdminService {
    Boolean checkAdmin(HttpServletRequest httpServletRequest);

    ResponseEntity<?> makeNewAdmin(String email, HttpServletRequest httpRequest);
}
