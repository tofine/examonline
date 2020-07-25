package com.exam.biz.impl;

import com.exam.biz.UserInfoBiz;
import com.exam.dao.UserInfoDao;
import com.exam.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoBizImpl implements UserInfoBiz{
    private UserInfoDao userInfoDao;

    @Override
    @Transactional
    public UserInfo getUserInfoById(String id) {
        return userInfoDao.findUserInfoById(id);
    }

    @Override
    @Transactional
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }
}
