package com.xmylx.cloud.sleuth.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("actuator/health")
    public String health(){
        return "SUCCESS";
    }
}
