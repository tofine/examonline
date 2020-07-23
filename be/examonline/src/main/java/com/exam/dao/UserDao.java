package com.exam.dao;

import com.exam.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUserByIdAndPwd(@Param("id") String id, @Param("pwd")String pwd);
    public int addUser(User user);
}
