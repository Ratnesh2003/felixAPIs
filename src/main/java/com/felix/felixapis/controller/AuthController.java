package com.felix.felixapis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/home")
    public String home() {
        return "This is home page";
    }

    @GetMapping("/admin")
    public String admin() {
        return "this is admin page";
    }
}
