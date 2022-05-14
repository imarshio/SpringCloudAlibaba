package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author masuo
 * @data 27/4/2022 下午2:33
 * @Description EurekaApplication
 */

@EnableEurekaServer
@SpringBootApplication
public class Eureka7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Eureka7001Application.class,args);
    }
}
