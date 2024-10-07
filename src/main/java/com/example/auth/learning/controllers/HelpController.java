package com.example.auth.learning.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {

    @PostMapping("/hi")
    public String syaHello(){
        return "Hello";
    }
}
