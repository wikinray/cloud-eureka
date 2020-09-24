package com.xmylx.cloud.sleuth.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SleuthProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SleuthProviderApplication.class,args);
    }
}
