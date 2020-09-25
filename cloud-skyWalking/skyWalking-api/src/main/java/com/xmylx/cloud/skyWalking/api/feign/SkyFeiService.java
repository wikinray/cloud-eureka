package com.xmylx.cloud.skyWalking.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "skyWalking-provider")
public interface SkyFeiService {

    @RequestMapping(value = "/skyWalking/provider/hello/sayHello",method = RequestMethod.GET)
    String sayHello(@RequestParam("name")String name);
}
