package com.exam.biz;

import com.exam.entity.User;

public interface UserBiz {
    public User getUserByLoginInfo(User user);
    public int registerUser(User user);
}
