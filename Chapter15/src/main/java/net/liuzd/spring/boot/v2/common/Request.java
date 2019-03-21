package net.liuzd.spring.boot.v2.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用请求体
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request<T> {
    private T data;

}
