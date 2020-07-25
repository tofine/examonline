package com.exam.biz;

import com.exam.entity.User;

public interface UserBiz {
    public User getUserByFormInfo(User user);
    public int registerUser(User user);
}
