package com.exam.biz.impl;

import com.exam.biz.UserBiz;
import com.exam.dao.UserDao;
import com.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBizImpl implements UserBiz {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User getUserByLoginInfo(User user) {
        return userDao.getUserByIdAndPwd(user.getUserId(),user.getPassword());
    }

    @Override
    @Transactional
    public int registerUser(User user) {
        return userDao.addUser(user);
    }

}
