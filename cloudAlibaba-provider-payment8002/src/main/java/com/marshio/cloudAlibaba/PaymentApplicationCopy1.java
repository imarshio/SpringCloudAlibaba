package com.marshio.cloudAlibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author masuo
 * @data 27/4/2022 下午5:26
 * @Description 支付模块集群1
 */

@EnableEurekaClient
@SpringBootApplication
public class PaymentApplicationCopy1 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationCopy1.class,args);
    }
}
