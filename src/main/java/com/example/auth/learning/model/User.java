package com.example.auth.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.Mapping;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class User extends BaseModel{

    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<Role> roles;
    private boolean isEmailVerified;

    public User(){

    }
    public User(String name, String email, String password){
        this.email = email;
        this.name = name;
        this.password = password;
        this.isEmailVerified = false;
        this.roles = new ArrayList<>();

    }
}
