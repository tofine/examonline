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
        User user1=userBiz.getUserByFormInfo(user);
        if(user1!=null){//登录成功
            String token= TokenUtil.save(user1,redisTemplate);
            return new Result(Result.OK,"登录成功",token);
        }
        return new Result(Result.FAIL,"登录失败",null);
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        log.info("user id="+user.getUserId()+" are register");
        int code = userBiz.registerUser(user);
        if (code==1)
            return new Result(Result.OK,"注册成功");
        else return new Result(Result.FAIL,"用户名已存在");
    }

    @DeleteMapping("/logout")
    public Result logout(HttpServletRequest request){
        if(TokenUtil.drop(request,redisTemplate))
            return new Result(Result.OK,"注销成功");
        return new Result(Result.FAIL,"未登录");
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
