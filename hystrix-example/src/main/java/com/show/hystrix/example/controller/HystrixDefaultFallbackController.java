package com.show.hystrix.example.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 演示默认服务降级处理
 *
 * @author show
 * @date 11:10 2019/6/12 @DefaultProperties //类全局服务降级处理
 */
@RestController
@RequestMapping("/Hystrix")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixDefaultFallbackController {
  @Autowired private RestTemplate restTemplate;

  /**
   * 演示 代码异常 Hystrix 处理
   *
   * @author show
   * @date 15:09 2019/6/12 @HystrixCommand 指定该接口会触发服务降级
   */
  @HystrixCommand
  @GetMapping("/DefaultError")
  public String defaultError() {

    throw new RuntimeException("发生异常");
  }

  /**
   * 访问超时触发服务降级，默认为1000毫秒
   *
   * @author show
   * @date 17:54 2019/6/12
   */
  @HystrixCommand
  @GetMapping("/OverTimeError")
  public String overTimeError() {
    //  该接口会延迟2秒返回
    String url = "http://SHOW-SERVER-EXAMPLE/HystrixServer/HystrixOverTimeTest";
    String responseData = restTemplate.getForObject(url, String.class);
    log.info("请求返回值为：{}", responseData);
    return responseData;
  }

  /**
   * 访问超时触发服务降级，默认为1000毫秒
   *
   * @author show
   * @date 17:54 2019/6/12 @HystrixCommand 自定服务降级策略 对应的默认配置在 HystrixCommandProperties 类里面
   */
  @HystrixCommand(
      commandProperties = {
        @HystrixProperty(
            name = "execution.isolation.thread.timeoutInMilliseconds",
            value = "1500") // 配置超时服务降级，默认为1000毫秒
      })
  @GetMapping("/OverTime")
  public String overTime() {
    //  该接口会延迟2秒返回
    String url = "http://SHOW-SERVER-EXAMPLE/HystrixServer/HystrixOverTimeTest";
    String responseData = restTemplate.getForObject(url, String.class);
    log.info("请求返回值为：{}", responseData);
    return responseData;
  }
  /** 下面是服务降级处理函数 */
  /**
   * 默认 服务降级 返回值
   *
   * @author show
   * @date 15:10 2019/6/12
   */
  private String defaultFallback() {

    log.info("HystrixDefaultFallbackController类发生异常，触发断路器默认返回");
    return "默认提示：太拥挤了，请稍后再试";
  }
}
