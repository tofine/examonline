package com.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public Map<String,String> test(){
        Map<String,String> map=new HashMap<>();
        map.put("message","helloWorld!");
        map.put("status","OK");
        return map;
    }

}
