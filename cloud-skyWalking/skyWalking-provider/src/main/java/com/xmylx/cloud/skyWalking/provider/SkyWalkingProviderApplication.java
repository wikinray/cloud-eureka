package com.xmylx.cloud.skyWalking.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SkyWalkingProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyWalkingProviderApplication.class,args);
    }
}
