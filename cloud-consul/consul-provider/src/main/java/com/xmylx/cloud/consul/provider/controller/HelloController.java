package com.xmylx.cloud.consul.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/consul/provider")
public class HelloController {

    @Value("${consul.provider.hello.name}")
    String message;


    @GetMapping("/hello/sayHello")
    public String sayHello(String name){
        return "hello,"+name+" ------->"+message;
    }
}
