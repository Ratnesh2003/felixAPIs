package com.felix.felixapis.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @RequestMapping("/userhome")
    @ResponseBody
    public String userHome() {
        return "User home page";
    }

    @RequestMapping("/adminhome")
    @ResponseBody
    public String adminHome() {
        return "Admin home page";
    }
}

