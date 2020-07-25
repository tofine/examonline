package com.exam.commons;

import com.exam.entity.User;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.UUID;

@Slf4j
@NoArgsConstructor
public class TokenUtil {

    //保存token和登录信息到redis服务器，并返回token
    public static String save(Object obj,RedisTemplate redisTemplate){
        String token=createToken();
        redisTemplate.opsForValue().set(token,obj,Duration.ofMinutes(30));
        return token;
    }

    public static String createToken(){
        return UUID.randomUUID().toString();
    }
    //删除
    public static boolean drop(String token,RedisTemplate redisTemplate){
        if(token==null) return false;
            redisTemplate.delete(token);
        return true;
    }

    public static boolean drop(HttpServletRequest request,RedisTemplate redisTemplate){
        String token=findTokenFromRequest(request);
        return drop(token,redisTemplate);
    }

    //检查token有效性并更新
    public static boolean checkAndUpdate(HttpServletRequest request,RedisTemplate redisTemplate){
        String token=findTokenFromRequest(request);
        Long expire=getExpire(token,redisTemplate);
        if(token!=null&&(expire>0||expire==-1)){
            update(token,redisTemplate);
            return true;
        }
        return false;
    }

    //获取登录信息
    public static User getUser(HttpServletRequest request,RedisTemplate redisTemplate){
        String token=TokenUtil.findTokenFromRequest(request);
        if(token==null) return null;
        return (User) redisTemplate.opsForValue().get(token);
    }
    //更新信息
    public static void update(String token,RedisTemplate redisTemplate){
        redisTemplate.expire(token, Duration.ofMinutes(30));
    }
    //获取剩余时长
    public static Long getExpire(String token,RedisTemplate redisTemplate){
        log.debug("getExpire "+token);
        return redisTemplate.getExpire(token);
    }

    //查找请求中携带的token
    public static String findTokenFromRequest(HttpServletRequest request){
        //从请求中获取token
        log.info("try to get token from header");
        String token=request.getHeader("token");
        if(token==null){
            String auth=request.getHeader("Authorization");
            if(auth!=null)
                token = auth.split(" ")[1];
            if(token==null){
                log.info("Cannot get token from http request header");
                if(token==null) {
                    log.info("try to get token from query parameter");
                    token = request.getParameter("token");
                    if (token == null) {
                        log.info("Cannot get token query parameter");
                        log.info("Authorization fair");
                    }
                }
            }
        }
        return token;
    }

}
