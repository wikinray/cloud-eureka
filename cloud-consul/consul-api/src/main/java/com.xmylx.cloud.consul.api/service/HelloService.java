package com.xmylx.cloud.consul.api.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "consul-provider")
public interface HelloService {

    @RequestMapping(value = "/hello/sayHello",method = RequestMethod.GET)
    String sayHello(@PathVariable("name")String name);

}
