# SpringCloudRibbonClient
# 使用RestTemplate和Feign进行微服务通信
 - 在微服务架构中，我们经常需要和其他服务进行通信实现数据交互，常用的方式有两种
   - RPC
   - HTTP
 - SpirngCloud 中，默认是使用HTTP进行微服务间通信，其中最常用的有两种实现形式
   - RestTemplate
   - Feign
 ## RestTempale
  - 其实在SpringWeb里面，已经原生支持了 RestTemplate，只不过我们一般使用的是把请求URL直接写死，而不是通过服务名的形式来调用，但是在微服务架构中，因为有了注册中心的存在，我们的负载均衡可以不需要使用第三方软件或者硬件实现了，所有，我们最佳的方式是经过服务名访问，请求到那个实例，由 Ribbon 的负载均衡策略来替我们决定。
### 第一种方式 (不推荐)

```java
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
    
    @PostMapping("/RibbonServer/1")
    public Map postRibbonServer1() {
        // 新建对象
        RestTemplate restTemplate = new RestTemplate();
        //请求目标地址
        String requestMsg = "方式一 POST 请求 RibbonServer";
        Map<String, Object> requestParam = new HashMap<>(16);
        requestParam.put("requestMsg", requestMsg);
        Map response = restTemplate.postForObject("http://localhost:9999/RibbonServer/RibbonTest", requestParam, Map.class);
        log.info("response={}", response);
        return response;
    }
```
> 直接使用 RestTemplate , Url写死  
### 请求方式二 
```java
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    
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
    
    @PostMapping("/RibbonServer/2")
    public Map postRibbonServer2() {
        // 获取IP地址
        ServiceInstance choose = loadBalancerClient.choose("RIBBON-SERVER");
        // 组装URL
        String url = String.format("http://%s:%s", choose.getHost(), choose.getPort() + "/RibbonServer/RibbonTest");
        RestTemplate restTemplate = new RestTemplate();
        String requestMsg = "方式二 POST 请求 RibbonServer";
        Map<String, Object> requestParam = new HashMap<>(16);
        requestParam.put("requestMsg", requestMsg);
        Map response = restTemplate.postForObject(url, requestParam, Map.class);
        log.info("response={}", response);
        return response;
    }
```
 > 利用 LoadBalancerClient 通过应用名获取 url，然后再使用 RestTemplate 请求
### 请求方式三 (推荐)
```java
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced //添加该注解，可以直接通过服务名找到对应的IP地址
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }
}
```

```java
    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("/RibbonServer/3")
    public Map getRibbonServer3() {

        String requestMsg = "方式三 GET 请求 RibbonServer";
        Map response = restTemplate.getForObject("http://RIBBON-SERVER/RibbonServer/RibbonTest?requestMsg=" + requestMsg, Map.class);
        log.info("response={}", response);
        return response;
    }
    
    @PostMapping("/RibbonServer/3")
    public Map postRibbonServer3() {

        String requestMsg = "方式三 POST 请求 RibbonServer";
        Map response = restTemplate.postForObject("http://RIBBON-SERVER/RibbonServer/RibbonTest", getRequestParam(requestMsg), Map.class);
        log.info("response={}", response);
        return response;
    }
```
> 利用 @LoadBalanced，可在 RestTemplate 里面直接使用应用名字


## Feign 
- Feign 是 Netflix 开发的声明式、模板化的 HTTP客户端, Feign 可以帮助我们更快捷、优雅地调用 HTTP API