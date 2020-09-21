package com.xmylx.cloud.demo.consumer.ribbon.controller;

import com.xmylx.cloud.demo.consumer.ribbon.feignService.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consul/consumer/ribbon")
public class HelloController {

    @Autowired
    HelloService helloService;
    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/hello/sayHello")
    public String sayHello(String name){
        return helloService.sayHelllo(name);
    }

    @GetMapping("/hello/sayHello2")
    public String sayHello2(String name){
        return restTemplate.getForObject("http://consul-provider/consul/provider/hello/sayHello?name="+name,String.class);
    }
}
