package com.xmylx.cloud.demo.consumer.discoverclient;


import com.ecwid.consul.v1.ConsulClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


@SpringBootApplication
public class ConsulConsumerDiscoveryclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerDiscoveryclientApplication.class,args);
    }


    @Bean
    @Order(value= Ordered.HIGHEST_PRECEDENCE)
    public MyConsulDiscoveryClient ConsulDiscoveryClient(ConsulClient client, ConsulDiscoveryProperties properties){
        return new MyConsulDiscoveryClient(client,properties);
    }
}
