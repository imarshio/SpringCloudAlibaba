package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author masuo
 * @date: 2022/07/06/ 下午9:40
 * @description
 */

@SpringBootApplication
public class Payment8010Application {

    public static void main(String[] args) {
        // ============注册中心============

        // 启动后会自动向nacos注册中心注册自己
        // NacosServiceRegistry : nacos registry, nacos-service 192.168.130.1:8010 register finished
        ConfigurableApplicationContext run = SpringApplication.run(Payment8010Application.class, args);

        while (true){

            System.out.println(run.getEnvironment().getProperty("user.name"));
            System.out.println(run.getEnvironment().getProperty("user.pwd"));
            System.out.println(run.getEnvironment().getProperty("spring.application.name"));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
