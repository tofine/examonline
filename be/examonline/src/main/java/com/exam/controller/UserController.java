package com.exam.controller;

import com.exam.biz.UserBiz;
import com.exam.commons.Result;
import com.exam.commons.TokenUtil;
import com.exam.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private UserBiz userBiz;
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result signIn(@RequestBody User user){
        log.info("user id="+user.getUserId()+" are login");
        //根据表单信息查询
        String token= TokenUtil.save(userBiz.getUserByFormInfo(user),redisTemplate);
        return Result.success("登录成功",token);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("user id="+user.getUserId()+" are register");
        userBiz.registerUser(user);
        return Result.success("注册成功");
    }

    @DeleteMapping("/logout")
    public Result logout(HttpServletRequest request){
        TokenUtil.drop(request,redisTemplate);
        return Result.success("注销成功");
    }


    @Autowired
    public void setTemplate(RedisTemplate<String, Object> template) {
        this.redisTemplate = template;
    }
    @Autowired
    public void setUserBiz(UserBiz userBiz) {
        this.userBiz = userBiz;
    }
}
