# SpringCloudSleuth
> 使用 Sleuth + Zipkin 监控微服务调用链
## 为什么追踪微服务调用
   - 在复杂的微服务架构中，我们通常实现一个功能，需要经过N个的微服务，万一接口出现问题，传统情况下，只能一层一层排查，效率慢不说，如果中间服务是不同的人或者团队开发，成本就太大了。
   - 而且有时候未必是接口异常，有时候接口调用时候过长，我们也是需要找原因的，能具体知道该功能实现经过的微服务单独的执行时间，会给接口性能调试，带来很大的便捷性。
   - Sleuth + zipkin 就是用来做这个的。
## Sleuth
> Spring Cloud Sleuth 主要功能就是在分布式系统中提供追踪解决方案，并且兼容支持了 zipkin，你只需要在pom文件中引入相应的依赖即可。
  
## Zipkin
> zipkin是一种分布式跟踪系统。它有助于收集解决微服务架构中的延迟问题所需的时序数据。它管理这些数据的收集和查找。Zipkin的设计基于 Google Dapper论文。

## 步骤
1. 去 Zipkin官网 下载docker镜像或者下载jar运行，也可以下载源代码打包运行
    - docker
        ```sh
        docker run -d -p 9411:9411 openzipkin/zipkin
        ```
    - java (必须是jdk8以上) 
        ```sh
        curl -sSL https://zipkin.io/quickstart.sh | bash -s
        java -jar zipkin.jar
        ```
    - 从Source运行
        ```
        # get the latest source
        git clone https://github.com/openzipkin/zipkin
        cd zipkin
        # Build the server and also make its dependencies
        ./mvnw -DskipTests --also-make -pl zipkin-server clean install
        # Run the server
        java -jar ./zipkin-server/target/zipkin-server-*exec.jar
         ```
2. 启动后打开 http://localhost:9411 即可进入Zipkin管理页面
3. 需要追踪的微服务上添加jar
    ```xml
    <!-- spring-cloud-starter-sleuth 和 spring-cloud-sleuth-zipkin 的集合-->
    <dependency>  
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-zipkin</artifactId>
    </dependency>
    ```
 4. 添加配置
     ```yaml
    spring:
      sleuth:
        sampler:
          #percentage: 1.0 #2.0版本前
          probability: 1.0 #全部收集，默认只收集10% 
      zipkin:
        base-url: http://127.0.0.1:9411  #zipkin访问地址
    ```
5. 重启确定微服务 

- 这个时候，你就可以在zipkin的页面看到整条调用链
- 而且从日志里面可以看到几行追踪信息日志 格式是[server-name, main-traceId,sub-spanId,boolean]
```
2019-06-28 14:42:56.218  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.netflix.config.ChainedDynamicProperty  : Flipping property: server.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647
2019-06-28 14:42:56.261  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.n.u.concurrent.ShutdownEnabledTimer    : Shutdown hook installed for: NFLoadBalancer-PingTimer-server
2019-06-28 14:42:56.263  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.netflix.loadbalancer.BaseLoadBalancer  : Client: server instantiated a LoadBalancer: DynamicServerListLoadBalancer:{NFLoadBalancer:name=server,current list of Servers=[],Load balancer stats=Zone stats: {},Server stats: []}ServerList:null
2019-06-28 14:42:56.271  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.n.l.DynamicServerListLoadBalancer      : Using serverListUpdater PollingServerListUpdater
2019-06-28 14:42:56.308  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.netflix.config.ChainedDynamicProperty  : Flipping property: server.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647
2019-06-28 14:42:56.314  INFO [sleuth,a15bde2f6b1b90a1,d3a0c5006f386fba,false] 32492 --- [nio-9412-exec-1] c.n.l.DynamicServerListLoadBalancer      : DynamicServerListLoadBalancer for client server initialized: DynamicServerListLoadBalancer:{NFLoadBalancer:name=server,current list of Servers=[localhost:8000],Load balancer stats=Zone stats: {defaultzone=[Zone:defaultzone;	Instance count:1;	Active connections count: 0;	Circuit breaker tripped count: 0;	Active connections per server: 0.0;]
},Server stats: [[Server:localhost:8000;	Zone:defaultZone;	Total Requests:0;	Successive connection failure:0;	Total blackout seconds:0;	Last connection made:Thu Jan 01 08:00:00 CST 1970;	First connection made: Thu Jan 01 08:00:00 CST 1970;	Active Connections:0;	total failure count in last (1000) msecs:0;	average resp time:0.0;	90 percentile resp time:0.0;	95 percentile resp time:0.0;	min resp time:0.0;	max resp time:0.0;	stddev resp time:0.0]
]}ServerList:org.springframework.cloud.netflix.ribbon.eureka.DomainExtractingServerList@335f3466
```
- 第一个参数：服务结点名称；
- 第二个参数：一条链路唯一的ID，叫TraceID
- 第三个参数：链路中每一环的ID，叫做SpanID
- 第四个参数：是否将信息输出到Zipkin等服务收集和展示。

 刷新zipkin页面 
 ![](https://image-show.oss-cn-shenzhen.aliyuncs.com/%E7%AE%80%E4%B9%A6%E5%9B%BE%E7%89%87/zipkin.png)
 - Service Name 微服务名
 - Span Name 调用的接口名 
    - 我微服务里面调用的是Server服务的SendServer接口 http://Server/SendServer
 - Lookback 时间区间 
 - 下面两个是 正则匹配和时间配置 可以指定查看调用的URL 或者指定耗时超过多少或者耗时最长的前N个调用链
 - 最下面的则是调用的请求链，点进去即可看到该次调用再每个服务所花费的世界
 
![](https://image-show.oss-cn-shenzhen.aliyuncs.com/%E7%AE%80%E4%B9%A6%E5%9B%BE%E7%89%87/zipkin2.png)
> 我没在Server里面加入Sleuth组件，所以里面只显示了我接口调用出去到返回的时间
- 从该图可以分析出
  - 整个接口花费了 877.096ms	
  - 中间处理花费了 694.941ms
- 点进去的话，还可以看到更多信息
- 如果调用链比较长，他的层次会更多，这样你就可以看到每一个微服务之间的调用，各自花费的时间，进行优化。
- 里面更多详细的信息介绍，有空再总结一下。