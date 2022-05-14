package com.marshio.cloudAlibaba.service.impl;

import com.marshio.cloudAlibaba.dao.PaymentDao;
import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author masuo
 * @data 24/4/2022 下午4:24
 * @Description 支付实现类
 */

@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * resource注解是Java自带的依赖注入
     */
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
