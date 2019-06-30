# Shell
![](https://img.shields.io/badge/jdk-1.8.0_151-9cf.svg)
![](https://img.shields.io/badge/maven-3.5.0-ff69b4.svg)

#说明
- 该项目脚本能直接运行Eureka注册中心和Zipkin可视化链路追踪，因为这两个组件都是直接运行，不需要个性化处理
- Eureka默认为集群启动，运行3个实例，相互注册，对应的端口为8761、8762、8763
  - 启动成功后访问：
       - http://127.0.0.1:8761
       - http://127.0.0.1:8762
       - http://127.0.0.1:8763
- Zipkin是在官方下载的jar包运行，端口为9411.
    - 启动成功后访问：
       - http://127.0.0.1:9411