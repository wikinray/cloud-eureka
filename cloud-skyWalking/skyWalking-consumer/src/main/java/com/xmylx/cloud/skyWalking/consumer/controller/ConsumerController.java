package com.xmylx.cloud.skyWalking.consumer.controller;

import com.xmylx.cloud.skyWalking.api.feign.SkyFeiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/skyWalking/consumer")
public class ConsumerController {

    private static final Logger log= LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private SkyFeiService skyFeiService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/helloFeign")
    public String helloFeign(String name){
        log.info("client sent  feign style ,param={}",name);
        String result=skyFeiService.sayHello(name);
        log.info("client received  feign style ,result={}",result);
        return result;
    }

    @GetMapping("/helloRestTemplate")
    public String helloRestTemplate(String name){
        log.info("client sent  RestTemplate style ,param={}",name);
        String url="http://sleuth-provider/sleuth/provider/hello/sayHello?name="+name;
        String result=restTemplate.getForObject(url,String.class);
        log.info("client received  RestTemplate style ,result={}",result);
        return result;
    }
}
