package com.show.spring.cloud.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.show.spring.cloud.hystrix.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 *演示服务请求熔断处理
 * @author show
 * @date 22:25 2019/6/14
 * @DefaultProperties  //类全局服务降级处理
 */
@RestController
@RequestMapping("/Hystrix")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixFuseController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 演示 代码异常 Hystrix 处理
     * @author show
     * @date 15:09 2019/6/12
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //断路器最少请求数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间，到了以后，断路器会变成半开关形态，如果再次请求失败，则继续断开
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 错误率，假如请求数在 requestVolumeThreshold 数上，错误达到该值，则进入断开状态
    })
    @GetMapping("/fuse")
    public ServerResponse fuseError(@RequestParam("number") int number) {

        if (number % 2 == 0) {
            return ServerResponse.createBySuccessMessage("success");
        } else {
            String url = "http://SERVER/HystrixServer/HystrixOverTimeTest";
            ServerResponse responseData = restTemplate.getForObject(url, ServerResponse.class);
            log.info("请求返回值为：{}", responseData);
            return responseData;
        }

    }

    /**下面是服务降级处理函数*/
    /**
     * 默认 服务降级 返回值
     * @author show
     * @date 15:10 2019/6/12
     */
    private ServerResponse defaultFallback() {

        return ServerResponse.createByDefaultFallback();
    }
}
