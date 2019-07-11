package com.show.zuul.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * 启动类
 *
 * @author show @EnableZuulProxy 路由注解
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

  public static void main(String[] args) {

    SpringApplication.run(ZuulApplication.class, args);
  }

  /**
   * 实现动态路由
   *
   * @author show
   * @date 20:40 2019-06-26
   */
  @ConfigurationProperties("zuul")
  @RefreshScope
  public ZuulProperties zuulProperties() {

    return new ZuulProperties();
  }
}
