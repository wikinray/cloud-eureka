package com.xmylx.cloud.consul.api.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-provider")
public interface HelloService {

    @RequestMapping(value = "/provider/hello/sayHello",method = RequestMethod.GET)
    String sayHello(@RequestParam("name")String name);

}
