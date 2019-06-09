package com.show.spring.cloud.ribbon.client.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Frign
 * @author show
 * @date 16:16 2019/6/9
 */
@FeignClient(name = "ribbon-server")
public interface RibbonServerClient {
    /**
     * 测试请求 RibbonServer
     * @Method: GET
     * @author show
     * @date 16:26 2019/6/9
     * @param requestMsg
     * @return java.util.Map
     */
    @GetMapping("/RibbonServer/RibbonTest")
    Map getRibbonServer(@RequestParam("requestMsg") String requestMsg);

    /**
     * 测试请求 RibbonServer
     * @Method: POST
     * @author show
     * @date 16:26 2019/6/9
     * @param requestMsg
     * @return java.util.Map
     */
    @PostMapping("/RibbonServer/RibbonTest")
    Map PostRibbonServer(@RequestBody Map requestMsg);

}
