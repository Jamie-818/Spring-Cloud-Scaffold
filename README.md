# Spring-Cloud-Scaffold (SpringCloud脚手架)

---

 - 该项目主要提供 SpringCloud 微服务的搭建脚手架，便于大家可以快速的搭建一个简单的微服务架构。
 
---

## 该项目暂时只包含以下组件

### Eureka 注册中心
  - spring-cloud-eureka(注册中心)
    > 启动即可供其他微服务注册
### Config 配置中心
  - spring-cloud-config
    > 配置启动即可供其他微服务使用外部配置
  - spring-cloud-config-client
    > 配置中心测试项目
### Ribbon 负载均衡
  - spring-cloud-ribbon-server
    > 提供接口供 ribbon-client 调用
  - spring-cloud-ribbon-client
    > 演示如果通过 RestTemplate 及Feign 调用其他微服务
