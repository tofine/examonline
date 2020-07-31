package com.exam.interceptor;

import com.exam.commons.Result;
import com.exam.commons.TokenUtil;
import com.exam.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI()+ " handlerInterceptor preHandle()");
        User user= TokenUtil.getUser(request,redisTemplate);
        log.info("user role:"+user.getRole());
        if(user==null||!user.getRole().equals("business")){
            ObjectMapper mapper=new ObjectMapper();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            mapper.writeValue(response.getWriter(), Result.error("权限不足"));
            return false;
        }
        return true;
    }
}
