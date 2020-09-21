package com.xmylx.cloud.consul.consumer.controller;

import com.xmylx.cloud.consul.api.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul/consumer/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(String name){
        return helloService.sayHello(name);
    }
}
