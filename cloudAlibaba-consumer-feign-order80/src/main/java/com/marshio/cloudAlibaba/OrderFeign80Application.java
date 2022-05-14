package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author masuo
 * @date: 2022/05/11/ 下午9:25
 * @description 使用feign，激活并开启feign服务
 */
@SpringBootApplication
@EnableFeignClients
public class OrderFeign80Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderFeign80Application.class, args);
    }
}
