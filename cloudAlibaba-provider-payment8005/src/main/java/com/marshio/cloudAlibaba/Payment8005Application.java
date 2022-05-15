package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author masuo
 * @date: 2022/05/15/ 下午10:28
 * @description EnableCircuitBreaker 开启Hystrix，服务降级、服务容错
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class Payment8005Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment8005Application.class, args);
    }
}
