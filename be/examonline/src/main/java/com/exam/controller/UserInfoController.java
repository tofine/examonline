package com.exam.controller;

import com.exam.biz.UserInfoBiz;
import com.exam.commons.Result;
import com.exam.commons.TokenUtil;
import com.exam.entity.User;
import com.exam.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RestController
public class UserInfoController {
    private UserInfoBiz userInfoBiz;
    private RedisTemplate<String,Object> redisTemplate;

    /**
     *更新用户基本信息
     * @param
     * @param userInfo 表单信息
     * @return
     * @throws IOException
     */
     @PostMapping("/userInfo")
    public Result updateUserInfo(@RequestPart(required = false,value = "file") MultipartFile file, UserInfo userInfo,HttpServletRequest request) throws IOException {
         User user=TokenUtil.getUser(request,redisTemplate);
         userInfo.setUserId(user.getUserId());
         if(file!=null)
             userInfo.setHeadSculpture(file.getBytes());
         userInfoBiz.updateUserInfo(userInfo);
         return Result.success("更新个人信息成功");
    }

    @GetMapping("/loginInfo")
    public Result getLoginInfo(HttpServletRequest request){
        User user= TokenUtil.getUser(request,redisTemplate);
        log.info("get user from redis >> "+user.toString());
        UserInfo info=userInfoBiz.getUserInfoById(user.getUserId());
        return Result.success("查询成功",info);
    }

    @Autowired
    public void setUserInfoBiz(UserInfoBiz userInfoBiz) {
        this.userInfoBiz = userInfoBiz;
    }
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
