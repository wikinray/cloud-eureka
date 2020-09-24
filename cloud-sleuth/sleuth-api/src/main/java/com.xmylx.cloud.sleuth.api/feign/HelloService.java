package com.xmylx.cloud.sleuth.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sleuth-provider")
public interface HelloService {

    @RequestMapping(value = "/sleuth/provider/hello/sayHello",method = RequestMethod.GET)
    String sayHello(@RequestParam("name")String name);
}
