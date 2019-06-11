package com.show.spring.cloud.ribbon.controller;

import com.show.spring.cloud.ribbon.client.RibbonServerClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示 Feign 的用法
 * @author show
 * @date 23:41 2019/6/7
 */
@RestController
@RequestMapping("/Feign")
@Slf4j
public class FeignController {
    @Autowired
    private RibbonServerClient ribbonServerClient;

    /**
     * Feign 访问微服务测试
     * @author show
     * @date 16:42 2019/6/9
     * @return java.util.Map
     */
    @GetMapping("/RibbonServer")
    public Map getRibbonServer() {

        String requestMsg = "Feign GET 请求 RibbonServer";
        Map response = ribbonServerClient.getRibbonServer(requestMsg);
        log.info("response={}", response);
        return response;
    }

    /**
     * Feign 访问微服务测试
     * @author show
     * @date 16:42 2019/6/9
     * @return java.util.Map
     */
    @PostMapping("/RibbonServer")
    public Map postRibbonServer1() {

        String requestMsg = "Feign Post 请求 RibbonServer";
        Map<String, Object> map = new HashMap<>(16);
        map.put("requestMsg", requestMsg);
        Map response = ribbonServerClient.postRibbonServer(map);
        log.info("response={}", response);
        return response;
    }
}
