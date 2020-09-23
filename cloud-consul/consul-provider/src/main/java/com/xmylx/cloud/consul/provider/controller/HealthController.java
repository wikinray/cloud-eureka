package com.xmylx.cloud.consul.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/actuator/health")
    public String health(){
        return "SUCCESS";
    }
}
