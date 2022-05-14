package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author masuo
 * @data 27/4/2022 下午5:26
 * @Description 支付模块集群3
 */

@EnableEurekaClient // 只适用于Eureka注册中心客户端
@SpringBootApplication
@EnableDiscoveryClient // 适用于各种注册中心客户端
public class Payment8003Application {
    public static void main(String[] args) {
        SpringApplication.run(Payment8003Application.class,args);
    }
}
