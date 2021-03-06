package com.show.server.example.controller;

import com.show.server.example.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 提供给 Hystrix 做Client端访问数据
 *
 * @author show
 * @date 11:14 2019/6/12
 */
@RestController
@RequestMapping("/HystrixServer")
@Slf4j
public class HystrixController {
  /**
   * 超时Server接口 @Method: GET
   *
   * @author show
   * @date 15:38 2019/6/9
   */
  @GetMapping("/HystrixOverTimeTest")
  public ServerResponse hystrixOverTimeTest() {

    log.info("GET 访问 HystrixOverTimeTest 请求成功");
    // 设置睡眠时间
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ServerResponse.createBySuccessMessage("GET 访问 HystrixOverTimeTest 请求成功");
  }

  @GetMapping("/HystrixFeign")
  public ServerResponse hystrixFeign() {

    log.info("GET 访问 HystrixFeign 请求成功");
    return ServerResponse.createBySuccessMessage("GET 访问 HystrixFeign 请求成功");
  }
}
