package com.marshio.cloudAlibaba.cotroller;

import com.marshio.cloudAlibaba.VO.ResponseBean;
import com.marshio.cloudAlibaba.dao.PaymentDao;
import com.marshio.cloudAlibaba.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author masuo
 * @data 24/4/2022 下午4:27
 * @Description TODO
 */

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentDao paymentDao;

    @PostMapping(value = "/payment/create")
    public ResponseBean<Integer> create(Payment payment) {
        int result = paymentDao.create(payment);
        log.info("插入结果 ：" + result);
        if (result > 0) {
            //parameterized
            return new ResponseBean<>(200, "success", result);
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
            return new ResponseBean<>(200, "success", payment);
        } else {
            return new ResponseBean<>(408, "失败了哦", null);
        }
    }

}
