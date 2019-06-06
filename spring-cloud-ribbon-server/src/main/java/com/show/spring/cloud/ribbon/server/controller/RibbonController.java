package com.show.spring.cloud.ribbon.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 提供给Client端访问数据
 * @author show
 * @date 22:59 2019/6/6
 */
@RestController
@RequestMapping("RibbonServer")
@Slf4j
public class RibbonController {

    @GetMapping("RibbonGetTest")
    public Map<String, Object> RibbonGetTest(String requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "RibbonGetTest 请求成功");
        responseMap.put("data", "");
        return responseMap;
    }

    @PostMapping("RibbonPostTest")
    public Map<String, Object> RibbonPostTest(String requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        Map<String, Object> responseMap = new HashMap<>(16);
        responseMap.put("status", "00");
        responseMap.put("msg", "RibbonPostTest 请求成功");
        responseMap.put("data", "");
        return responseMap;
    }
}
