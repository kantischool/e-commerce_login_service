package com.example.auth.learning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Token extends BaseModel{

    @OneToOne
    private User user;
    private String value;
    private Date expiredAt;
    private boolean isDeleted;
}
