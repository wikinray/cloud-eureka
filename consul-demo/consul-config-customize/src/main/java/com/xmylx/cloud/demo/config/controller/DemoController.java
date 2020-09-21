package com.xmylx.cloud.demo.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/consul/config")
public class DemoController {

    @Value("${consul.config.test.name}")
    String name;

    @GetMapping("/demo/getName")
    public String getName(){
        return name;
    }
}
