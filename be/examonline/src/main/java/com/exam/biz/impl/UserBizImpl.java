package com.exam.biz.impl;

import com.exam.biz.UserBiz;
import com.exam.biz.UserInfoBiz;
import com.exam.dao.UserDao;
import com.exam.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserBizImpl implements UserBiz {

    private UserDao userDao;
    private UserInfoBiz userInfoBiz;

    @Autowired
    public void setUserInfoBiz(UserInfoBiz userInfoBiz) {
        this.userInfoBiz = userInfoBiz;
    }

    @Override
    @Transactional
    public User getUserByFormInfo(User user) {
        return userDao.getUserByIdAndPwd(user.getUserId(),user.getPassword());
    }

    @Override
    @Transactional
    public int registerUser(User user) {
        int i = userDao.addUser(user);
        log.debug("init personal basic user info >> id:"+user.getUserId());
        int j = userInfoBiz.initUserInfo(user.getUserId());
        return (i==0||j==0)?0:1;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
