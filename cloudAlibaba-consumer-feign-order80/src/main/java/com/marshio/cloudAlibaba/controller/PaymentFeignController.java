package com.marshio.cloudAlibaba.controller;

import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.service.PaymentFeignService;
import com.marshio.cloudAlibaba.vo.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author masuo
 * @date: 2022/05/11/ 下午10:03
 * @description
 */
@RestController
public class PaymentFeignController {

    @Resource
    PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/get/{id}")
    public ResponseBean<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 超时测试
     *
     * @return port
     */
    @GetMapping(value = "/consumer/timeOutTest")
    public ResponseBean<String> timeOut() {
        // 客户端（OpenFeign）一般默认等待 2 秒，不然就会报错 查看 connection-timeout
        return new ResponseBean<>(paymentFeignService.timeOut());
    }
}
