package com.marshio.cloudAlibaba.service;

import com.marshio.cloudAlibaba.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author masuo
 * @data 24/4/2022 下午4:23
 * @Description 服务
 */

@Service
public interface PaymentService {

    /**
     * 创建支付
     *
     * @param payment s
     * @return i
     */
    int create(Payment payment);

    /**
     * 获取支付信息
     *
     * @param id by
     * @return Payment
     */
    Payment getPaymentById(@Param("id") Long id);


    /**
     * 超时测试
     * @param id id
     * @return string
     */
    String timeOut(Integer id);


    public String runTimeExceptionTest(Integer id);
}
