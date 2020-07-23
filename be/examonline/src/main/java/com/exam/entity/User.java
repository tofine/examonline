package com.exam.entity;

import lombok.Data;

@Data
public class User{

    private String userId;

    private String password;

    private String role;

    public User(){
        this.role="customer";
    }

}
