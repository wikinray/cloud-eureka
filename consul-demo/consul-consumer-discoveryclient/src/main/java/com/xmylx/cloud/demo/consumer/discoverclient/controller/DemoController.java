package com.xmylx.cloud.demo.consumer.discoverclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    DiscoveryClient discoveryClient;


    @GetMapping("/demo/getServer")
    public List<ServiceInstance> getServer(String serverId){
        return discoveryClient.getInstances(serverId);
    }
}
