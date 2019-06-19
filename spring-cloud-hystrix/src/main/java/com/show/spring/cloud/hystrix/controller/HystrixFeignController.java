package com.show.spring.cloud.hystrix.controller;

import com.show.spring.cloud.hystrix.client.RibbonServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Feign 使用 Hystrix 演示类
 * @author xuanweiyao
 * @date 18:22 2019/6/19
 */
@RestController
@RequestMapping("/Hystrix")
public class HystrixFeignController {
    @Autowired
    private RibbonServerClient ribbonServerClient;

    @GetMapping("/FeignHystrixTestOverTime")
    public String feignHystrixTestOverTime() {

        return ribbonServerClient.overTime();
    }

}
