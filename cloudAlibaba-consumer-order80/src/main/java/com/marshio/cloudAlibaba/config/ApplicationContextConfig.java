package com.marshio.cloudAlibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author masuo
 * @data 25/4/2022 下午1:37
 * @Description 配置类
 */

@Configuration
public class ApplicationContextConfig {

    /**
     * 需要将RestTemplate注入容器
     * @return RestTemplate
     */
    @Bean
    @LoadBalanced// 如果直接向注册中心调取服务的化，需要加上此注解
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
