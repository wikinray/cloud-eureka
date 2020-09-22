package com.xmylx.cloud.consul.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/consumer")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        System.out.println("------------success access test method!---------------");
        Enumeration<String> headerNames=request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String key=headerNames.nextElement();
            System.out.println(key+": "+request.getHeader(key));
        }
        return "success access test method!!";
    }

    @RequestMapping("/accessProvider")
    public String accessProvider(HttpServletRequest request){
        return restTemplate.getForObject("http://consul-provider/provider/test",String.class);
    }
}
