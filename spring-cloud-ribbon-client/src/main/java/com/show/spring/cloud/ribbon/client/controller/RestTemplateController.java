package com.show.spring.cloud.ribbon.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * 演示 RestTemplate 的用法
 * @author show
 * @date 23:41 2019/6/7
 */
@RestController
@RequestMapping("/RestTemplate")
@Slf4j
public class RestTemplateController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 第一种方式(直接使用 RestTemplate,URL 写死)
     * @Method: GET
     * <p>缺点：无法负载均衡</>
     * @author show
     * @date 13:58 2019/6/9
     */
    @GetMapping("/RibbonServer/1")
    public Map getRibbonServer1() {
        // 新建对象
        RestTemplate restTemplate = new RestTemplate();
        //请求目标地址
        String requestMsg = "方式一 GET 请求 RibbonServer";
        Map response = restTemplate.getForObject("http://localhost:9999/RibbonServer/RibbonTest?requestMsg=" + requestMsg, Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 第一种方式(直接使用 RestTemplate,URL 写死)
     * @Method: POST
     * <p>缺点：无法负载均衡</>
     * @author show
     * @date 13:58 2019/6/9
     */
    @PostMapping("/RibbonServer/1")
    public Map postRibbonServer1() {
        // 新建对象
        RestTemplate restTemplate = new RestTemplate();
        //请求目标地址
        String requestMsg = "方式一 POST 请求 RibbonServer";
        Map response = restTemplate.postForObject("http://localhost:9999/RibbonServer/RibbonTest", getRequestParam(requestMsg), Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 第二种方式(利用 LoadBalancerClient 通过应用名获取 url，然后再使用 RestTemplate 请求)
     * @Method: GET
     * @author show
     * @date 15:17 2019/6/9
     */
    @GetMapping("/RibbonServer/2")
    public Map getRibbonServer2() {
        // 获取IP地址
        ServiceInstance choose = loadBalancerClient.choose("RIBBON-SERVER");
        String requestMsg = "方式二 GET 请求 RibbonServer";
        String url = String.format("http://%s:%s", choose.getHost(), choose.getPort() + "/RibbonServer/RibbonTest?requestMsg=" + requestMsg);
        RestTemplate restTemplate = new RestTemplate();
        Map response = restTemplate.getForObject(url, Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 第二种方式(利用 LoadBalancerClient 通过应用名获取 url，然后再使用 RestTemplate 请求)
     * @Method: POST
     * @author show
     * @date 15:17 2019/6/9
     */
    @PostMapping("/RibbonServer/2")
    public Map postRibbonServer2() {
        // 获取IP地址
        ServiceInstance choose = loadBalancerClient.choose("RIBBON-SERVER");
        // 组装URL
        String url = String.format("http://%s:%s", choose.getHost(), choose.getPort() + "/RibbonServer/RibbonTest");
        RestTemplate restTemplate = new RestTemplate();
        String requestMsg = "方式二 POST 请求 RibbonServer";
        Map response = restTemplate.postForObject(url, getRequestParam(requestMsg), Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 第三种方式(利用 @LoadBalanced，可在 RestTemplate 里面直接使用应用名字)
     * 1.新建 RestTemplateConfig 类
     * 2.添加 RestTemplate Bean
     * 3.添加 @LoadBalanced注解，
     * 4.注入 RestTemplate 对象
     * @Method: GET
     * @author show
     * @date 15:17 2019/6/9
     */
    @GetMapping("/RibbonServer/3")
    public Map getRibbonServer3() {

        String requestMsg = "方式三 GET 请求 RibbonServer";
        Map response = restTemplate.getForObject("http://RIBBON-SERVER/RibbonServer/RibbonTest?requestMsg=" + requestMsg, Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 第三种方式(利用 @LoadBalanced，可在 RestTemplate 里面直接使用应用名字)
     * 1.新建 RestTemplateConfig 类
     * 2.添加 RestTemplate Bean
     * 3.添加 @LoadBalanced注解，
     * 4.注入 RestTemplate 对象
     * @Method: GET
     * @author show
     * @date 15:17 2019/6/9
     */
    @PostMapping("/RibbonServer/3")
    public Map postRibbonServer3() {

        String requestMsg = "方式三 POST 请求 RibbonServer";
        Map response = restTemplate.postForObject("http://RIBBON-SERVER/RibbonServer/RibbonTest", getRequestParam(requestMsg), Map.class);
        log.info("response={}", response);
        return response;
    }

    /**
     * 获取请求参数
     * @author show
     * @date 15:47 2019/6/9
     * @param requestMsg 请求内容
     */
    private static Map<String, Object> getRequestParam(String requestMsg) {

        Map<String, Object> requestParam = new HashMap<>(16);
        requestParam.put("requestMsg", requestMsg);
        log.info("请求参数为:{}", requestParam);
        return requestParam;
    }
}

