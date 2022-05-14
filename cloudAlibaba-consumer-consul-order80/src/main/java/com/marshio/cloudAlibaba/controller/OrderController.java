package com.marshio.cloudAlibaba.controller;

import com.marshio.cloudAlibaba.vo.ResponseBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author masuo
 * @data 13/5/2022 下午2:21
 * @Description consul调用服务
 */

@RestController
public class OrderController {

    public static final String PAYMENT = "http://consul-provider-payment";

    @Resource
    DiscoveryClient discoveryClient;

    @RequestMapping("/consumer/get")
    public ResponseBean<List<String>> getPayment() {
        List<String> services = discoveryClient.getServices();
        List<String> stringList = new ArrayList<>(services);

        return new ResponseBean<>(stringList);
    }
}
