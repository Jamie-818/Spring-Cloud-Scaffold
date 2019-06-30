package com.show.hystrix.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.show.hystrix.example.client.SpringCloudServerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign 使用 Hystrix 演示类
 * @author show
 * @date 18:22 2019/6/19
 */
@RestController
@Slf4j
@RequestMapping("/Hystrix")
public class HystrixFeignController {

    @Autowired
    private SpringCloudServerClient springCloudServerClient;

    @GetMapping("/FeignHystrixTestOverTime")
    @HystrixCommand
    public String feignHystrixTestOverTime() {

        return springCloudServerClient.feignOverTime();
    }

    @GetMapping("/FeignHystrixTest")
    @HystrixCommand
    public String feignHystrixTest() {

        return springCloudServerClient.feign();
    }

}
