package com.show.config.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 启动类
 *
 * @author show
 * @date 10:51 2019/6/11 @EnableConfigServer 该注解指定本微服务是配置中心, 其他需要用到注册中心的，都必须添加 Client 注解
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

  public static void main(String[] args) {

    SpringApplication.run(ConfigApplication.class, args);
  }
}
