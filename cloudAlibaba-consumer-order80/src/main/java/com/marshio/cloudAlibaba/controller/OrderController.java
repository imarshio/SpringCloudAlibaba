package com.marshio.cloudAlibaba.controller;

import com.marshio.cloudAlibaba.entities.Payment;
import com.marshio.cloudAlibaba.vo.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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

    //http://localhost:8001/payment/getPaymentById/11
    public static final String PAYMENT_URL = "http://localhost:8001";

    @GetMapping("/comsumer/payment/create")
    public ResponseBean<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ResponseBean.class);
    }

    @GetMapping("/comsumer/payment/get/{id}")
    public ResponseBean<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("获取id为" + id);
        String url = PAYMENT_URL + "/payment/getPaymentById/" + id;
        log.info("url:" + url);
        ResponseBean forObject = restTemplate.getForObject(url, ResponseBean.class);
        Payment payment = new Payment();
        // httpClientRPC(url);

        assert forObject != null;
        log.info(forObject.getClass().getName());
        return forObject;
    }

    /**
     * HttpClient请求调用远程服务方法
     * @param url 请求地址
     * @return  CloseableHttpResponse
     */
    public CloseableHttpResponse httpClientTest(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try{
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                String rt = EntityUtils.toString(response.getEntity(), "UTF8");
                log.info(rt);
            }
        }catch (Exception e){
            log.info("没获取到");
        }
        log.info(String.valueOf(response));
        return response;
    }
}
