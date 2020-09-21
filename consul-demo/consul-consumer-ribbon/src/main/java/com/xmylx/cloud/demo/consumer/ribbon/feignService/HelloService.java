package com.xmylx.cloud.demo.consumer.ribbon.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("consul-provider")
public interface HelloService {

    @RequestMapping(value = "/consul/provider/hello/sayHello",method= RequestMethod.GET)
    String sayHelllo(@RequestParam("name")String name);
}
