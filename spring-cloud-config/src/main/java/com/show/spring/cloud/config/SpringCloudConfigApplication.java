package com.show.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableDiscoveryClient // 除了注册中心，其他微服务都是 Client, 都要添加该注解
@EnableConfigServer // 该注解指定本微服务是配置中心,其他需要用到注册中心的，都必须添加 Client 注解
public class SpringCloudConfigApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudConfigApplication.class, args);
    }

}
