# Eureka注册中心

## 介绍

Spring Cloud Eureka是Spring对netflix公司的Eureka的二次封装，它实现了服务治理的功能，
Spring Cloud Eureka分为客户端和服务端，服务端就是Eureka服务注册中心，客户端完成微服务向Eureka服务的注册与发现。
Eureka服务端与客户端均是Java语言编写。

> 注意，Eureka官方已经停止了更新。但并没有停用。

### Eureka服务端

Eureka服务端更像一个中介，从服务提供者获取服务（即注册），当服务消费者来消费时给他提供服务（服务调用）。

注册中心之间通过复制的方式完成数据的同步。

我们只需要使用```@EnableEurekaServer```就可以将此模块设置为一个Eureka的服务端，各个节点启动后，会注册到Eureka注册中心。

具体代码如下：

```java
@EnableEurekaServer // 标注这是一个Eureka服务端
@SpringBootApplication
public class Eureka7001Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Application.class,args);
    }
}
```


### Eureka客户端

在一个微服务项目中，存在服务提供者和服务消费者，他们都是Eureka的客户端。Eureka客户端用于简化与Eureka Server的交互，Eureka客户端是一个内置的、使用负载均衡算法的轮询的负载均衡器。

Eureka客户端每隔30s（默认）就会向注册中心发送一次心跳，如果注册中心在90s（默认）内没有收到某个服务的心跳，这个服务就会被删除。

#### 服务提供者

服务提供者，他想提供服务就需要向外界展示自己，此即将自己的服务注册给注册中心，这里客户端实行了他的注册权限。

#### 服务消费者

服务消费者去消费服务，此时消费者就需要知道去哪里进行消费，此时他就需要询问注册中心去哪里消费，这里消费者客户端实行了他的发现服务的权限。



没有Eureka之前的服务调用

![image-20220429150200471](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220429150200.png)

有Eureka之后的服务调用（下图中，服务A是服务调用方，服务B是服务提供方）

![image-20220429151609847](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220429151609.png)



### 服务发现

Eureka提供了```@EnableEurekaClient```用于服务发现与注册，同时Spring还为我们提供了```@EnableDiscoveryClient```同样用于服务的发现与注册。

#### 异同

他们都是用于服务发现与注册的，但是```@EnableEurekaClient```只能用于Eureka，而```@EnableDiscoveryClient```能应用于其他注册中心。



如下：

```java
@EnableEurekaClient // 只适用于Eureka注册中心客户端
@SpringBootApplication
@EnableDiscoveryClient // 适用于各种注册中心客户端
public class Payment8003Application {
    public static void main(String[] args) {
        SpringApplication.run(Payment8003Application.class,args);
    }
}
```

#### 使用

```java
@RestController
@Slf4j
public class PaymentController {

    // 服务发现客户端，其作用是主动发现并获取服务
    // 注意不要引错报：org.springframework.cloud.client.discovery.DiscoveryClient;
    @Resource
    private DiscoveryClient discoveryClient;


    @GetMapping(value = "/getServices")
    public ResponseBean<Object> getDiscoveryList() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println("服务列表：" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUDALIBABA-PAYMENT-SERVICE");
        instances.forEach(instanceInfo -> {
            System.out.println(instanceInfo + "**" + instanceInfo.getHost() + "**" + instanceInfo.getUri());
        });
        return new ResponseBean<>(200,"成功",services);
    }
}
```

### Eureka自我保护

![image-20220429153631174](https://masuo-github-image.oss-cn-beijing.aliyuncs.com/image/20220429153631.png)

当你看到这一串红色的字时，就说明他开启了自我保护机制。

那么什么是自我保护机制呢？

> 默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例（默认90秒)。但是当网络分区故障发生(延时、卡顿、拥挤)时，微服务与EurekaServer之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。
>
> Eureka通过“自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。在自我保护模式中，Eureka Server会保护服务注册表中的信息，不再注销任何服务实例。它的设计哲学就是宁可保留错误的服务注册信息，也不盲目注销任何可能健康的服务实例。

一句话讲解∶好死不如赖活着。使用自我保护模式：可以让Eureka集群更加的健壮、稳定。

如何禁用？

只需修改Eureka注册中心的配置即可

```yaml
eureka:
  server:
    # 禁用自我保护机制
    enable-self-preservation: false
```

