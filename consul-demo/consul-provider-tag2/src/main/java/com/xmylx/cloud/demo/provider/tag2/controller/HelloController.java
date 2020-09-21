package com.xmylx.cloud.demo.provider.tag2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consul/provider")
public class HelloController {

    @GetMapping("/hello/sayHello")
    public String sayHello(String name){
        return "hello,"+name+". I am tag2";
    }
}
