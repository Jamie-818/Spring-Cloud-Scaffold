package com.show.spring.cloud.hystrix.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign
 * @author show
 * @date 16:16 2019/6/9
 * fallback 指定服务降级处理类
 */
@FeignClient(name = "server", fallback = RibbonServerClient.RibbonServerFallback.class)
public interface RibbonServerClient {

    /**
     * 测试请求 Feign 使用 Hystrix
     * @Method: GET
     * @author show
     * @date 18:12 2019/6/19
     * @return java.lang.String
     */
    @GetMapping("/HystrixServer/HystrixOverTimeTest")
    String overTime();

    /**
     * 服务降级触发类
     * @author xuanweiyao
     * @date 18:15 2019/6/19
     * @Component 作为组件
     */
    @Component
    class RibbonServerFallback implements RibbonServerClient {

        @Override
        public String overTime() {
            // 触发服务降级返回
            return null;
        }
    }

}