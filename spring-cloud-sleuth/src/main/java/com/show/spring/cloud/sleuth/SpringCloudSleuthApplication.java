package com.show.spring.cloud.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 * @author show
 */
@SpringBootApplication
public class SpringCloudSleuthApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudSleuthApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

}
