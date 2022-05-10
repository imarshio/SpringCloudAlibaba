package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author masuo
 * @date: 2022/05/10/ 下午10:57
 * @description
 */

@SpringBootApplication
@EnableEurekaServer
public class Eureka7003Application {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7003Application.class, args);
    }
}
