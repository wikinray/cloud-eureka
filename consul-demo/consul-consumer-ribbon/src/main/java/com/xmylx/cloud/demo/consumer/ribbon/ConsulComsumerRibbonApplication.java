package com.xmylx.cloud.demo.consumer.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.xmylx.cloud.demo.consumer.ribbon.feignService")
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulComsumerRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulComsumerRibbonApplication.class,args);
    }


    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
