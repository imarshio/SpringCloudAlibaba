package com.marshio.cloudAlibaba;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;
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

        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            if (beanDefinitionName.contains("nacos")) {
                System.out.println("111111111" + beanDefinitionName);
            }
        }
        while (true){

            Properties properties = new Properties();
            properties.put("serverAddr", "127.0.0.1:8848");
            //try {
            //    NacosConfigService nacosConfigService = new NacosConfigService(properties);
            //    String config = nacosConfigService.getConfig("common.yml", "DEFAULT_GROUP", 3000);
            //    System.out.println(config);
            //} catch (NacosException e) {
            //    e.printStackTrace();
            //}
            //System.out.println(run.getEnvironment().getProperty("user.name"));
            //System.out.println(run.getEnvironment().getProperty("user.pwd"));
            //System.out.println(run.getEnvironment().getProperty("spring.application.name"));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
