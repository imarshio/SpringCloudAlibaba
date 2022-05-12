package com.marshio.cloudAlibaba.controller;

import com.marshio.cloudAlibaba.vo.ResponseBean;
import com.marshio.cloudAlibaba.dao.PaymentDao;
import com.marshio.cloudAlibaba.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author masuo
 * @data 24/4/2022 下午4:27
 * @Description 值赋服务
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentDao paymentDao;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public ResponseBean<Integer> create( Payment payment) {
        int result = paymentDao.create(payment);
        log.info("插入结果 ：" + result);
        if (result > 0) {
            //parameterized
            return new ResponseBean<>(200, "success,端口为：" + port, result);
        } else {

            return new ResponseBean<>(408, "失败了哦", null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public ResponseBean<Payment> getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentDao.getPaymentById(id);
        log.info("查询结果 ：" + payment);
        if (payment != null) {
            //parameterized
            return new ResponseBean<>(200, "success,端口为：" + port, payment);
        } else {
            return new ResponseBean<>(408, "失败了哦", null);
        }
    }

    /**
     * 超时测试
     * @return port
     */
    @GetMapping(value = "/payment/timeOutTest")
    public String timeOut() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
