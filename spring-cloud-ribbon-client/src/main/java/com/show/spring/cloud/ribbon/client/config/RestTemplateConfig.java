package com.show.spring.cloud.ribbon.client.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate bean
 * @author show
 * @date 15:26 2019/6/9
 */
@Component
public class RestTemplateConfig {
    /**
     * RestTemplate Bean 组件
     * @author show
     * @date 10:45 2019/6/11
     * @LoadBalanced 添加该注解，可以直接通过服务名找到对应的IP地址
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
