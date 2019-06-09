package com.show.spring.cloud.ribbon.server.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供给Client端访问数据
 * @author show
 * @date 22:59 2019/6/6
 */
@RestController
@RequestMapping("/RibbonServer")
@Slf4j
public class RibbonController {
    /**
     * 测试 GET访问是否正常
     * @Method: GET
     * @author show
     * @date 15:38 2019/6/9
     * @param requestMsg 入参
     */
    @GetMapping("/RibbonTest")
    public Map<String, Object> ribbonGetTest(String requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "GET访问RibbonServer 请求成功");
        responseMap.put("data", "你请求的数据=" + requestMsg);
        return responseMap;
    }

    /**
     * 测试 POST访问是否正常
     * @Method: POST
     * @author show
     * @date 15:38 2019/6/9
     * @param requestMsg 入参
     */
    @PostMapping("/RibbonTest")
    public Map<String, Object> ribbonPostTest(@RequestBody Map requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "POST访问RibbonServer 请求成功");
        responseMap.put("data", "你请求的数据=" + requestMsg);
        return responseMap;
    }
}
