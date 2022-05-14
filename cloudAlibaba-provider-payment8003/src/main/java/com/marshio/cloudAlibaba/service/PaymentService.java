package cloudAlibaba.service;

import com.marshio.cloudAlibaba.entities.Payment;
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
     * @param payment s
     * @return i
     */
    int create(Payment payment);

    /**
     * @param id by
     * @return Payment
     */
    Payment getPaymentById(@Param("id") Long id);
}
