package com.marshio.cloudAlibaba.config;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.xml.ws.soap.Addressing;
import java.util.Properties;

/**
 * @author masuo
 * @date: 2022/07/20/ 下午10:32
 * @description
 */

public class MyNacosConfigService {

    @Resource
    NacosConfigProperties nacosConfigProperties;

    @Bean
    public NacosConfigService getConfigService(){
        Properties properties = new Properties();
        properties.put("serverAddr",nacosConfigProperties.getServerAddr());

        try {
            return new NacosConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
