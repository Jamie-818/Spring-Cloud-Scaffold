package com.show.spring.cloud.server.controller;

import com.show.spring.cloud.server.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 提供给 Hystrix 做Client端访问数据
 * @author show
 * @date 11:14 2019/6/12
 */
@RestController
@RequestMapping("/HystrixServer")
@Slf4j
public class HystrixController {
    /**
     * 超时Server接口
     * @Method: GET
     * @author show
     * @date 15:38 2019/6/9
     */
    @GetMapping("/HystrixOverTimeTest")
    public ServerResponse hystrixOverTimeTest() {
        // 设置睡眠时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ServerResponse.createBySuccessMessage("GET 访问 HystrixOverTimeTest 请求成功");
    }

}
