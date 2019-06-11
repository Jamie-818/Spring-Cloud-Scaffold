package com.show.spring.cloud.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * @author show
 * @date 10:52 2019/6/11
 */
@SpringBootApplication
@EnableFeignClients
public class SpringCloudRibbonApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringCloudRibbonApplication.class, args);
    }

}
