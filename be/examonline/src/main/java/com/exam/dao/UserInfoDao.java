package com.exam.dao;

import com.exam.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao {
    //通过id查找用户信息
    public UserInfo findUserInfoById(String id);
    //新增用户信息
    public int addUserInfo(UserInfo userInfo);
    //更新用户信息
    public int updateUserInfo(UserInfo userInfo);
}
