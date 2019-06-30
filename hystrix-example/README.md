# SpringCloudHystrix
![](https://img.shields.io/badge/SpringBoot-2.1.5.RELEASE-brightgreen.svg)
![](https://img.shields.io/badge/SpringCloud-Greenwich.SR1-blue.svg)
![](https://img.shields.io/badge/jdk-1.8.0_151-9cf.svg)
![](https://img.shields.io/badge/maven-3.5.0-ff69b4.svg)

- 该项目功能
  - RestTemplate使用注解实现Hystrix服务降级
  - Feign使用配置实现Hystrix服务降级
  - 配置文件实现Hystrix服务降级演示
  - 配置文件实现Hystrix熔断演示

---
##实现步骤
 - 导入jar包
```xml
 <dependencies>
    <!--添加 hystrix 依赖-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>
    <!--添加 Feign 依赖-->
    <dependency><!--如果不适用Feign的话，不需要添加-->
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency> 
</dependencies>
```

### RestTemplate使用注解实现Hystrix服务降级
 - 在需要实现服务降级的接口类上添加 @HystrixCommand 注解,并且指定触发熔断时，执行的方法名(该方法必须在该类里面，不可以跨类)
     - 下面代码当 @HystrixCommand 指定的接口类发生异常或者接口请求出错或者请求超时(默认1000ms超时)，就会跳到指定的方法进行返回
     - 注意，该方法入参和出参类型要和接口方法都要一致
     - 可以在类上面用 @DefaultProperties 来指定默认fallback执行方法，相关演示代码在 HystrixDefaultFallbackController;
```java
@RestController
@RequestMapping("/Hystrix")
@Slf4j
public class HystrixFallbackController {
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 演示请求其他微服务 Hystrix 处理
     * @author show
     * @HystrixCommand 指定异常时执行什么方式
     */
    @HystrixCommand(fallbackMethod = "ribbonErrorFallback")
    @GetMapping("/ribbonError")
    public String ribbonError() {
        // 该URL请求为404
        String url = "http://Server/error";
        String responseData = restTemplate.getForObject(url, String.class);
        log.info("返回值为:{}", responseData);
        return responseData;
    }
    /**
     * 演示 代码异常 Hystrix 处理
     * @author show
     * @HystrixCommand 指定异常时执行什么方式
     */
    @HystrixCommand(fallbackMethod = "runtimeErrorFallback")
    @GetMapping("/runtimeError")
    public String runtimeError() {

        throw new RuntimeException("发生异常");
    }
    /**下面是服务降级处理函数*/
    /**
     * 代码异常返回
     * @author show
     */
    private String ribbonErrorFallback() {

        log.info("ribbon接口请求ribbon微服务异常，断路器启动");
        return "断路器降级启动，请求异常";
    }
    /**
     * 请求异常返回
     * @author show
     */
    private String runtimeErrorFallback() {

        log.info("runtimeError接口发生运行时异常，断路器启动");
        return "断路器降级启动，运行异常";
    }
}
```

### RestTemplate使用注解实现Hystrix熔断
-  @HystrixCommand 可以直接对单个接口开启熔断，只要添加四个配置
  - circuitBreaker.enabled = true //开启熔断
  - circuitBreaker.requestVolumeThreshold = 10  //断路器最少请求数
  - circuitBreaker.sleepWindowInMilliseconds = 10000 //休眠时间，到了以后，断路器会变成半开关形态，如果再次请求失败，则继续断开
  - circuitBreaker.errorThresholdPercentage = 60 //错误率，假如请求数在 requestVolumeThreshold 数上，错误达到该值，则进入断开状态
- 上面配置中，该接口只要在10次请求中，触发6次服务降级，就会进入熔断半开关状态10000ms(10s)，这时再请求，直接返回不执行业务逻辑，
- 10000ms后，再请求，如果依然触发服务降级，则继续进入半开关10000ms，否则则进入正常请求状态
```java
@RestController
@RequestMapping("/Hystrix")
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixFuseController {
    @Autowired
    private RestTemplate restTemplate;
    /**
     * 演示 代码异常 Hystrix 处理
     * @author show
     * @date 15:09 2019/6/12
     */
    @HystrixCommand(commandProperties = {
                                 @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //开启熔断
                                 @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //断路器最少请求数
                                 @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//休眠时间，到了以后，断路器会变成半开关形态，如果再次请求失败，则继续断开
                                 @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 错误率，假如请求数在 requestVolumeThreshold 数上，错误达到该值，则进入断开状态
                                 })
    @GetMapping("/fuse")
    public String fuseError(@RequestParam("number") int number) {

        if (number % 2 == 0) {
            return "success";
        } else {
            throw new RuntimeException();
        }

    }

    /**下面是服务降级处理函数*/
    /**
     * 默认 服务降级 返回值
     * @author show
     * @date 15:10 2019/6/12
     */
    private String defaultFallback() {

        log.info("fuse接口触发断路器，默认返回");
        return "默认提示：太拥挤了，请稍后再试";
    }
}
```

### Feign使用配置实现Hystrix服务降级 
- Feign逻辑代码在 com.show.spring.cloud.hystrix.client 包里面
- 使用 Feign 除了maven导入包以外，记得在启动类添加 @EnableFeignClients 注解
- 同样，每个接口都必须添加  @HystrixCommand 表明该接口使用Hystrix
```yaml
hystrix:
  command:
    default: #全局配置服务配置超时
      execution:
        isolation:
          thread:
            timeoutInMilliseconds: 60000 #访问超时时间
        timeout:
          enabled: true  #超时发生异常属性关闭
        circuitBreaker:
          enabled: true  //开启熔断
          requestVolumeThreshold: 10 //断路器最少请求数
          sleepWindowInMilliseconds: 10000 //休眠时间，到了以后，断路器会变成半开关形态，如果再次请求失败，则继续断开
          errorThresholdPercentage: 60 // 错误率，假如请求数在 requestVolumeThreshold 数上，错误达到该值，则进入断开状态
feign:
  hystrix:
    enabled: false #开启Feign使用Hystrix
ribbon: #设置ribbon超时时间
  ConnectTimeout: 250 #Ribbon的连接超时时间
  ReadTimeout: 2100  #Ribbon的数据读取超时时间
  maxAutoRetries: 0 #重试次数
```
> 
     这里有个坑，Feign必须配合Ribbon使用
     而且在Feign+Hystrix的情况下，不要把 hystrix.command.default.execution.isolation:.threadtimeoutInMilliseconds的值设的太低，该值要超过ribbon.ReadTimeout,建议N倍或者无限大，因为Ribbon有重试机制,
     不然会触发 HystrixRuntime异常(feign timed-out and fallback failed),网上找不到解决方法。
     怀疑是两个超时线程造成的冲突。
     我们把接口超时时间控制交给ribbon，分别设置 ribbon.ConnectTimeout和 ribbon.ReadTimeout即可。
     其中ribbon.ReadTimeout 的时间就是你设置的接口超时时间，hystrix的超时时间，有多大设置多大。

## 测试接口
````
http://localhost:9999/Hystrix/RibbonError          #当RestTemplate调用出现404时，触发fallback
http://localhost:9999/Hystrix/RuntimeError         #当程序出现RuntimeException，触发fallback
http://localhost:9999/Hystrix/DefaultError         #触发fallback默认返回
http://localhost:9999/Hystrix/OverTime             #正常返回，不触发fallback
http://localhost:9999/Hystrix/OverTimeError        #当RestTemplate调用超时，触发fallback
http://localhost:9999/Hystrix/Fuse?number=1        #触发触发fallback，当达到熔断条件，则接口进入半开关状态，下面访问依然会触发fallback
http://localhost:9999/Hystrix/Fuse?number=2        #正常返回，不触发fallback，当该接口触发熔断时，则不正常返回
http://localhost:9999/Hystrix/FeignHystrixTestOverTime  #当Feign调用超时，触发fallback
http://localhost:9999/Hystrix/FeignHystrixTest    #正常返回，触发fallback
````
