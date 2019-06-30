package com.show.ribbon.example;

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
public class RibbonApplication {

    public static void main(String[] args) {

        SpringApplication.run(RibbonApplication.class, args);
    }

}
