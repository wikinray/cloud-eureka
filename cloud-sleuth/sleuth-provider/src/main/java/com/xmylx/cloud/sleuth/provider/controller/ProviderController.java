package com.xmylx.cloud.sleuth.provider.controller;

import com.xmylx.cloud.sleuth.api.feign.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sleuth/provider")
public class ProviderController {

    private static final Logger log= LoggerFactory.getLogger(ProviderController.class);

    @GetMapping(value = "/hello/sayHello")
    public String sayHello(String name) {
        log.info("server received .参数={}",name);
        String result="hello,"+name;
        log.info("server received .参数={}",result);
        return result;
    }
}
