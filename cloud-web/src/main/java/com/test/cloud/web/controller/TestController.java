package com.test.cloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TestController {
    private Logger logger= LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response){
        String header=request.getHeader("X-Request-Acme");
        return "测试成功   >>>>>>"+header;
    }

    @RequestMapping(value = "/addParameter")
    public String example(HttpServletRequest request, HttpServletResponse response){
        String header=request.getParameter("example");
        return "测试成功   >>>>>>"+header;
    }


    ConcurrentHashMap<String, AtomicInteger> map=new ConcurrentHashMap<>();

    @RequestMapping(value = "/retry")
    public String testRetry(@RequestParam(name = "key")String key,@RequestParam(name = "count")int count){
        AtomicInteger num=map.computeIfAbsent(key, s->new AtomicInteger());
        int i=num.incrementAndGet();
        logger.warn("重试次数："+i);
        if(i<count){
            throw new RuntimeException("Deal with failure,please try again!");
        }
        map.clear();
        return "重试   >>>>>>"+count+"次成功!";
    }
}
