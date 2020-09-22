package com.xmylx.cloud.consul.gateway.controller;

import com.xmylx.cloud.consul.gateway.filter.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class TokenController {

    @GetMapping("/getToken/{name}")
    public String get(@PathVariable("name")String name){
        return JwtUtil.generaeToken(name);
    }
}
