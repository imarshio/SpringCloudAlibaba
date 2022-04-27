package com.marshio.cloudAlibaba.controller;

import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author masuo
 * @data 25/4/2022 下午1:29
 * @Description 订单 -
 * RPC（Remote Procedure Call Protocol） 远程程序调用协议，即调用远程机器的服务，例如 输入baidu.com 搜索
 * 服务调用
 * 1、传统方式，HttpClient
 */

@RestController
@Slf4j
public class OrderController {


    @Resource
    private RestTemplate restTemplate;

    /**
     * http://localhost:8001/payment/getPaymentById/11
     */
    public static final String PAYMENT_URL = "http://localhost:8001";

    /**
     * 消除泛型：https://www.bbsmax.com/A/1O5EY7PW57/
     *
     * @param payment 值赋
     * @return ResponseBean
     */
    @GetMapping("/consumer/payment/create")
    public ResponseBean<Payment> create(Payment payment) {
        // 如果直接 restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseBean.class); unchecked assignment:即未擦除泛型
        // 此时我们需要使用exchange来解决该问题

        ParameterizedTypeReference<ResponseBean<Payment>> typeRef = new ParameterizedTypeReference<ResponseBean<Payment>>() { };
        return restTemplate.exchange(PAYMENT_URL + "/payment/create", HttpMethod.POST, new HttpEntity<>(payment), typeRef).getBody();
    }

    /**
     * 错误示例
     * @param payment 自动注入的对象
     * @return ResponseBean
     */
    @GetMapping("/test/consumer/payment/create")
    public ResponseBean<Payment> createTest(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create",payment,ResponseBean.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public ResponseBean<Payment> getPayment(@PathVariable("id") Long id) {
        String url = PAYMENT_URL + "/payment/getPaymentById/" + id;
        log.info("请求地址为url:" + url);
        System.out.println(httpClientTest(url));
        ParameterizedTypeReference<ResponseBean<Payment>> typeReference = new ParameterizedTypeReference<ResponseBean<Payment>>() { };
        return restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<>(id),typeReference).getBody();
    }

    @GetMapping("/test/consumer/payment/get/{id}")
    public ResponseBean<String> getPaymentWithHttpClient(@PathVariable("id") Long id) {
        String url = PAYMENT_URL + "/payment/getPaymentById/" + id;
        System.out.println();
        ResponseBean<String> rbs = new ResponseBean<>(400,"失败了吗？");
        try {
            rbs = new ResponseBean<>(200,"成功",EntityUtils.toString(httpClientTest(url).getEntity(),"UTF8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rbs;
    }

    /**
     * HttpClient请求调用远程服务方法
     *
     * @param url 请求地址
     * @return CloseableHttpResponse
     */
    public CloseableHttpResponse httpClientTest(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (200 == response.getStatusLine().getStatusCode()) {
                String rt = EntityUtils.toString(response.getEntity(), "UTF8");
                log.info(rt);
            }
        } catch (Exception e) {
            log.info("没获取到");
        }
        log.info(String.valueOf(response));
        return response;
    }
}
