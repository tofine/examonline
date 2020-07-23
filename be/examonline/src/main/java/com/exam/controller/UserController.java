package com.exam.controller;

import com.exam.biz.UserBiz;
import com.exam.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserBiz userBiz;

    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public void setTemplate(RedisTemplate<String, Object> template) {
        this.redisTemplate = template;
    }
    @Autowired
    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }

    @PostMapping("/login")
    public String signIn(@RequestBody User user){
        log.info("user id="+user.getUserId()+" are login");
        User user1=userBiz.getUserByLoginInfo(user);
        if(user1!=null){//登录成功
            String token= UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(token,user1, Duration.ofMinutes(30));
            return token;
        }
        return null;
    }

    @PostMapping("/register")
    public int register(@RequestBody User user){
        log.info("user id="+user.getUserId()+" are register");
        return userBiz.registerUser(user);
    }
}
