package com.marshio.cloudAlibaba.service;

import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.vo.ResponseBean;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;

/**
 * @author masuo
 * @date: 2022/05/11/ 下午9:27
 * @description
 */
@Component
@FeignClient(value = "CLOUDALIBABA-PAYMENT-SERVICE")
public interface PaymentFeignService {

    // 服务提供方有什么接口功能，我们这里就提供什么功能

    /**
     * 根据订单id获取订单信息,
     * 调用过程，消费者发起调用-》controller --》 service ，找到CLOUDALIBABA-PAYMENT-SERVICE服务下的 /payment/getPaymentById/{id}地址对应的接口 --》调用服务提供方的controller
     * @param id 订单id
     * @return ResponseBean
     */
    @GetMapping(value = "/payment/getPaymentById/{id}")
    ResponseBean<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 超时测试
     * @return port
     */
    @GetMapping(value = "/payment/timeOutTest")
    public String timeOut();
}
