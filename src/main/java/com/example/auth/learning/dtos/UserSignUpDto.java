package com.example.auth.learning.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignUpDto {
    private String name;
    private String email;
    private String password;
}
