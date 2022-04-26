package com.marshio.cloudAlibaba.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author masuo
 * @data 25/4/2022 下午1:31
 * @Description 前后端传递对象
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBean<T> {
    private Integer code;
    private String message;
    private T date;

    public ResponseBean(Integer code, String message) {
        this(code, message, null);
    }
}
