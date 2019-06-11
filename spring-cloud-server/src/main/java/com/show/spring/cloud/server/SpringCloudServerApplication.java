package com.show.spring.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 启动类
 * @author show
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringCloudServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudServerApplication.class, args);
    }

}
