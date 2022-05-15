package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author masuo
 * @data 13/5/2022 上午11:32
 * @Description 使用consul,将服务注册到consul注册中心
 */

@SpringBootApplication
@EnableDiscoveryClient
public class Payment8004Application {

    public static void main(String[] args) {
        SpringApplication.run(Payment8004Application.class, args);
    }
}
