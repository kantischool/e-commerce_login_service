package com.example.auth.learning.controllers;

import com.example.auth.learning.dtos.LoginRequestDto;
import com.example.auth.learning.dtos.UserSignUpDto;
import com.example.auth.learning.model.Token;
import com.example.auth.learning.model.User;
import com.example.auth.learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User userSignUp(@RequestBody UserSignUpDto userSignUpDto){
       return userService.singUp(userSignUpDto);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto){
       return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token){
        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validatetoken/{token}")
    public boolean validateToken(@PathVariable("token") String token){
        return userService.validateToken(token);
    }
}
