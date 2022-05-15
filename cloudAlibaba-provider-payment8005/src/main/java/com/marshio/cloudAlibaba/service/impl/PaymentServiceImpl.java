package com.marshio.cloudAlibaba.service.impl;

import com.marshio.cloudAlibaba.dao.PaymentDao;
import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    /**
     * 超时测试
     * HystrixCommand：指定服务降级方法，即 fallbackMethod，当服务相应超时，或服务运行出错都会进入指定的方法
     * HystrixProperty：指定超时时间上限，就是服务等待多长时间就会进入fallback method
     *
     * @param id id
     * @return string
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeOut(Integer id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Thread.currentThread().getName() + ",id = " + id;
    }

    /**
     * 超时测试
     * HystrixCommand：指定服务降级方法，即 fallbackMethod，当服务相应超时，或服务运行出错都会进入指定的方法
     * HystrixProperty：指定超时时间上限，就是服务等待多长时间就会进入fallback method
     *
     * @param id id
     * @return string
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler")
    public String runTimeExceptionTest(Integer id) {
        int i = 10/0;
        return Thread.currentThread().getName() + ",id = " + id;
    }

    public String paymentTimeoutHandler(Integer id) {
        return Thread.currentThread().getName() + ",系统超时/报错，请稍后再试。";
    }
}
