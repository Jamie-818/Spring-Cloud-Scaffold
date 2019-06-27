package com.show.spring.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 启动类
 * @author show
 * @EnableEurekaServer 该服务为注册中心, 其他微服务都必须标明自己是 Client
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEurekaApplication.class, args);
    }

}
