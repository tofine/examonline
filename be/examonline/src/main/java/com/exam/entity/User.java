package com.exam.entity;

import lombok.Data;

/**
 * 用户账户信息
 */
@Data
public class User{

    private String userId;

    private String password;

    private String role;

    public User(){
        this.role="customer";
    }

}
