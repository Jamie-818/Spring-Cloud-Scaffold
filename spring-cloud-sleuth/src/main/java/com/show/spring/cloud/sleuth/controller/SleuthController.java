package com.show.spring.cloud.sleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 调用server接口，测试zipkin是否跟踪到链路
 * @author show
 * @date 22:21 2019-06-27
 */
@RestController
@Slf4j
public class SleuthController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/SendServer")
    public String sleuthSendServer() {

        String responseData = restTemplate.getForObject("http://server/Sleuth", String.class);
        log.info(responseData);
        return responseData;
    }
}
