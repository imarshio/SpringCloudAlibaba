package com.marshio.cloudAlibaba.dao;

import com.marshio.cloudAlibaba.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author masuo
 * @data 24/4/2022 下午3:42
 * @Description dao
 * 推荐使用@Mapper注解
 */

@Mapper
public interface PaymentDao {

    /**
     * @param payment s
     * @return i
     */
    int create(Payment payment);

    /**
     * @param id by
     * @return Payment
     */
    Payment getPaymentById(@Param("id") Long id);

    // U


    // D
}
