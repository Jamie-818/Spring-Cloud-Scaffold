package com.show.sleuth.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 启动类
 *
 * @author show
 */
@SpringBootApplication
public class SleuthApplication {

  public static void main(String[] args) {

    SpringApplication.run(SleuthApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {

    return new RestTemplate();
  }
}
