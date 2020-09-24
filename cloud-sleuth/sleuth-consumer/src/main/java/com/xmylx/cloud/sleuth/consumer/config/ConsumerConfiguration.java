package com.xmylx.cloud.sleuth.consumer.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ConsumerConfiguration {

    @Autowired
    BeanFactory beanFactory;

    //注册固定大小的线程池
    @Bean
    public ExecutorService executorService(){
        //todo  需手动创建线程池
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        return new TraceableExecutorService(beanFactory,executorService);
    }
}
