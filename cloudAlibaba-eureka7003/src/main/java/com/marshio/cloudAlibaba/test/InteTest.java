package com.marshio.cloudAlibaba.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author masuo
 * @date: 2022/05/10/ 下午11:04
 * @description
 */
public class InteTest {

    public static void main(String[] args) {
        try {
            InetAddress[] allByName = InetAddress.getAllByName("127.0.0.1");
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
