package com.xmylx.cloud.consul.gateway.controller;

import com.xmylx.cloud.consul.gateway.filter.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class TokenController {
    private static Logger logger= LoggerFactory.getLogger(TokenController.class);

    @GetMapping("/getToken/{name}")
    public String get(@PathVariable("name")String name){
        logger.info(" getToken test>>>>>>");
        return JwtUtil.generateToken(name);
    }
}
