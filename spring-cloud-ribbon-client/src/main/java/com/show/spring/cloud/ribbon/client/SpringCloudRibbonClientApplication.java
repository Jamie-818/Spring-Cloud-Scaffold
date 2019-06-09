package com.show.spring.cloud.ribbon.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCloudRibbonClientApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudRibbonClientApplication.class, args);
    }

}
