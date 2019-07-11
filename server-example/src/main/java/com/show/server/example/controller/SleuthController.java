package com.show.server.example.controller;

import com.show.server.example.vo.ServerResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试被Sleuth服务调用能否在zikpin查看调用链
 *
 * @author show
 * @date 22:26 2019-06-27
 */
@RestController
public class SleuthController {
  @GetMapping("Sleuth")
  public ServerResponse back() {

    return ServerResponse.createBySuccessMessage("调用成功");
  }
}
