package com.exam.entity;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class TestRedisCache {

    @Cacheable(value = "test2",key = "'testCache2'")
    public List<Integer> invokeCache(){
        System.out.println("invoke cache");
        return Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8});
    }

}
