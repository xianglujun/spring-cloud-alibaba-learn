package org.spring.cloud.alibaba.learn.resource.server.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xianglujun
 * @date 2023/6/12 10:51
 */
@Getter
@ToString
@AllArgsConstructor
public class R<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> R<T> of(int code, String msg, T data) {
        R<T> r = new R<>(code, msg, data);
        return r;
    }
}
