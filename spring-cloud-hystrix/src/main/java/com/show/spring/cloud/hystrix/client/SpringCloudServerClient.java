package com.show.spring.cloud.hystrix.client;

import com.show.spring.cloud.hystrix.client.fallback.SpringCloudServerClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Feign
 * @author show
 * @date 16:16 2019/6/9
 * fallback 指定服务降级处理类
 */
@FeignClient(name = "server", fallbackFactory = SpringCloudServerClientFallback.class)
public interface SpringCloudServerClient {

    /**
     * 测试请求 Feign 使用 Hystrix
     * @Method: GET
     * @author show
     * @date 18:12 2019/6/19
     * @return java.lang.String
     */
    @GetMapping("/HystrixServer/HystrixOverTimeTest")
    String feignOverTime();

    /**
     * 测试请求
     * @author xuanweiyao
     * @date 16:30 2019/6/20
     * @return java.lang.String
     */
    @GetMapping("/HystrixServer/HystrixFeign")
    String feign();



}