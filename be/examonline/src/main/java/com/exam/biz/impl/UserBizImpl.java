package com.exam.biz.impl;

import com.exam.biz.UserBiz;
import com.exam.biz.UserInfoBiz;
import com.exam.dao.UserDao;
import com.exam.entity.User;
import com.exam.exception.UserExistException;
import com.exam.exception.UserOrPwdErrorException;
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
         User user1=userDao.getUserByIdAndPwd(user.getUserId(),user.getPassword());
         if(user1==null) throw  new UserOrPwdErrorException();
         return user1;
    }

    @Override
    @Transactional
    public int registerUser(User user) {
        try{
            userDao.addUser(user);
            log.debug("init personal basic user info >> id:"+user.getUserId());
            userInfoBiz.initUserInfo(user.getUserId());
        }catch (RuntimeException e){
            throw new UserExistException();
        }
        return 1;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
