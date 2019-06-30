package com.show.ribbon.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Feign
 * @author show
 * @date 16:16 2019/6/9
 */
@FeignClient(name = "SHOW-SERVER-EXAMPLE")
public interface RibbonServerClient {
    /**
     * 测试请求 RibbonServer
     * @Method: GET
     * @author show
     * @date 16:26 2019/6/9
     * @param requestMsg 请求参数
     * @return java.util.Map
     */
    @GetMapping("/RibbonServer/RibbonTest")
    String getRibbonServer(@RequestParam("requestMsg") String requestMsg);

    /**
     * 测试请求 RibbonServer
     * @Method: POST
     * @author show
     * @date 16:26 2019/6/9
     * @param requestMsg 请求参数
     * @return java.util.Map
     */
    @PostMapping("/RibbonServer/RibbonTest")
    String postRibbonServer(@RequestBody Map requestMsg);

}
