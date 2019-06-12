package com.show.spring.cloud.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
     * 测试 GET访问是否正常
     * @Method: GET
     * @author show
     * @date 15:38 2019/6/9
     * @param requestMsg 入参
     */
    @GetMapping("/HystrixTest")
    public Map<String, Object> hystrixServerGetTest(String requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "GET 访问 HystrixServer 请求成功");
        responseMap.put("data", "你请求的数据:" + requestMsg);
        return responseMap;
    }

    /**
     * 测试 POST访问是否正常
     * @Method: POST
     * @author show
     * @date 15:38 2019/6/9
     * @param requestMsg 入参
     */
    @PostMapping("/HystrixTest")
    public Map<String, Object> hystrixPostTest(@RequestBody Map requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "POST 访问 HystrixServer 请求成功");
        responseMap.put("data", "你请求的数据:" + requestMsg);
        return responseMap;
    }
}
