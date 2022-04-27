package com.marshio.cloudAlibaba;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author masuo
 * @data 24/4/2022 下午3:15
 * @Description 主启动类
 * 约定 > 配置 > 编码
 * 1、建model
 * 2、改POM
 * 3、写YML
 * 4、主启动
 * 5、业务类 ==> vue <-----> controller -> service -> dao -> sql
 *                   JSON
 */

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.marshio.cloudAlibaba.dao")
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
