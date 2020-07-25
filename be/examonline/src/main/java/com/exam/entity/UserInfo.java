package com.exam.entity;

import com.exam.commons.Sex;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户基本信息
 */
@Data
@NoArgsConstructor
public class UserInfo {

    @JsonIgnore
    private String userId;  //用户id

    private String nickname; //昵称
    private byte[] headSculpture;  //头像访问路径
    private Sex sex;       //性别
    private String introduce;  //个人介绍

    private String realName; //真实姓名
    private String mail;     //常用邮箱
    private String phoneNumber;//电话
    private String qqNumber;   //qq号

    public void setSex(int sex){
        this.sex=Sex.class.getEnumConstants()[sex];
    }
}


