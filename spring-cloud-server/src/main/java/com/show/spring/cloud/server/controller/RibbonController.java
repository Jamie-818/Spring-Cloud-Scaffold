package com.show.spring.cloud.server.controller;

import com.show.spring.cloud.server.vo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 提供给Ribbon做Client端访问数据
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
    public ServerResponse ribbonGetTest(String requestMsg) {

        log.info("接收到数据为：{}", requestMsg);
        return ServerResponse.createBySuccess("GET 访问 RibbonServer 请求成功", "你请求的数据:" + requestMsg);
    }

    /**
     * 测试 POST访问是否正常
     * @Method: POST
     * @author show
     * @date 15:38 2019/6/9
     * @param requestMsg 入参
     */
    @PostMapping("/RibbonTest")
    public ServerResponse ribbonPostTest(@RequestBody Map requestMsg) {

        log.info("接收到数据为：{}", requestMsg);

        return ServerResponse.createBySuccess("POST 访问 RibbonServer 请求成功", "你请求的数据:" + requestMsg);
    }
}
