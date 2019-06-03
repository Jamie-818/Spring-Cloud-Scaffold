package com.show.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // 该服务为注册中心,其他微服务都必须标明自己是 Client
public class SpringCloudEurekaApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudEurekaApplication.class, args);
    }

}
