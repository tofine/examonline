package com.exam.dao;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //通过用户名和密码获取用户
    public User getUserByIdAndPwd(@Param("id") String id, @Param("pwd")String pwd);
    //添加用户
    public int addUser(User user);
}
