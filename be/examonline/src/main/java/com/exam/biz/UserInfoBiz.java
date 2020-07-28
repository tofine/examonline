package com.exam.biz;

import com.exam.entity.UserInfo;

public interface UserInfoBiz {
    public UserInfo getUserInfoById(String id);
    public int initUserInfo(String userId);
    public int updateUserInfo(UserInfo userInfo);
}
