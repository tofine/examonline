package com.exam.interceptor;

import com.exam.commons.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor{

    private String REDIRECT_URL="/user/login";
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info(request.getRequestURI()+ " handlerInterceptor preHandle()");

        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Content-Type", "application/json");

        if(TokenUtil.checkAndUpdate(request,redisTemplate)){
            log.info("Authorization access");
            return true;
        }
        log.info("Authorization fair");
        response.sendRedirect(REDIRECT_URL);
        return false;
    }
}
