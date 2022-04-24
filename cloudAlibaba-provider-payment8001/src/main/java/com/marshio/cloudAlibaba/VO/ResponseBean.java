package com.marshio.cloudAlibaba.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author masuo
 * @data 24/4/2022 下午3:36
 * @Description 返回实体
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
