package com.xmylx.cloud.sleuth.consumer.controller;

import com.xmylx.cloud.sleuth.api.feign.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/sleuth/consumer")
public class ConsumerController {

    private static final Logger log= LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private HelloService helloService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExecutorService executorService;

    @GetMapping("/helloFeign")
    public String helloFeign(String name){
        log.info("client sent  feign style ,param={}",name);
        String result=helloService.sayHello(name);
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

    @GetMapping("/helloNewThread")
    public String helloNewThread(String name) throws ExecutionException, InterruptedException {
        log.info("client sent  Thread style ,param={}",name);
        Future future=executorService.submit(()->{
            log.info("client sent  进入子线程 ,param={}",name);
            String result=helloService.sayHello(name);
            return result;
        });
        String result=(String) future.get();
        log.info("client received  main Thread style ,result={}",result);
        return result;
    }
}
