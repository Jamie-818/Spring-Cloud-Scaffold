# Spring-Cloud-Scaffold (SpringCloud脚手架)

---

 - 该项目主要提供 SpringCloud 微服务的搭建脚手架，便于大家可以快速的搭建一个简单的微服务架构。
 
---
## 该项目的启动顺序为
1. spring-cloud-eureka 提供注册服务
2. spring-cloud-config 提供配置中心服务
3. spring-cloud-server 提供接口调用

---

## 该项目暂时只包含以下组件

### Eureka 注册中心
  - [spring-cloud-eureka](https://github.com/MrXuan3168/Spring-Cloud-Scaffold/tree/master/spring-cloud-eureka)
    - 启动即可供其他微服务注册
    
### Config 配置中心
  - [spring-cloud-config](https://github.com/MrXuan3168/Spring-Cloud-Scaffold/tree/master/spring-cloud-config)
    - 配置启动即可供其他微服务使用外部配置
    
### Server 调试接口提供
  - [spring-cloud-server](https://github.com/MrXuan3168/Spring-Cloud-Scaffold/tree/master/spring-cloud-server)
    - 提供接口测试
    - 配置写在配置中心，验证配置中心是否正常

### Ribbon 负载均衡
  - [spring-cloud-ribbon](https://github.com/MrXuan3168/Spring-Cloud-Scaffold/tree/master/spring-cloud-ribbon)
    - 演示如果通过 RestTemplate 及Feign 调用其他微服务

### Hystrix 断路器
  - [spring-cloud-hystrix](https://github.com/MrXuan3168/Spring-Cloud-Scaffold/tree/master/spring-cloud-hystrix)
    - 演示服务降级及处理，提供了运行异常、微服务间调用异常、微服务间调用超时的处理案例。
