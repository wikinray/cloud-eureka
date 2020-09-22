package com.xmylx.cloud.consul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulGatewayApplication.class,args);
    }

   /* @Bean
    public ServerCodecConfigurer serverCodecConfigurer(){
        return ServerCodecConfigurer.create();
    }*/
}
