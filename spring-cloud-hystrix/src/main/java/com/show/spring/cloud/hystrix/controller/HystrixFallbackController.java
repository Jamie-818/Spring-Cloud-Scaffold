package com.show.spring.cloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * 演示Hystrix
 * @author show
 * @date 11:10 2019/6/12
 */
@RestController
@RequestMapping("/Hystrix")
@Slf4j
public class HystrixFallbackController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 演示请求其他微服务 Hystrix 处理
     * @author show
     * @date 15:09 2019/6/12
     * @HystrixCommand 指定异常时执行什么方式
     */
    @HystrixCommand(fallbackMethod = "ribbonErrorFallback")
    @GetMapping("/ribbonError")
    public String ribbonError() {
        // 该URL请求为404
        String url = "http://Server/error";
        String responseData = restTemplate.getForObject(url, String.class);
        log.info("返回值为:{}", responseData);
        return responseData;
    }

    /**
     * 演示 代码异常 Hystrix 处理
     * @author show
     * @date 15:09 2019/6/12
     * @HystrixCommand 指定异常时执行什么方式
     */
    @HystrixCommand(fallbackMethod = "runtimeErrorFallback")
    @GetMapping("/runtimeError")
    public String runtimeError() {

        throw new RuntimeException("发生异常");
    }

    /**下面是服务降级处理函数*/
    /**
     * 代码异常返回
     * @author show
     * @date 15:11 2019/6/12
     */
    private String ribbonErrorFallback() {

        return "断路器降级启动，请求异常";
    }

    /**
     * 请求异常返回
     * @author show
     * @date 15:10 2019/6/12
     */
    private String runtimeErrorFallback() {

        return "断路器降级启动，运行异常";
    }

}
