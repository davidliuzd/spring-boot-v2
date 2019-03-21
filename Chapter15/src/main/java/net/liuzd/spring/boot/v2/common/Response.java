package net.liuzd.spring.boot.v2.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.liuzd.spring.boot.v2.constants.ReqResConsts;

/**
 * 通用响应体
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private T data;
    private String code;
    private String msg;

    public Response<T> success(String msg){
        this.code = ReqResConsts.SUCCESS_CODE;
        this.msg = msg;
        return this;
    }
    public Response<T> success(){
        return success(null);
    }

    public Response<T> fail(String msg){
        this.code = ReqResConsts.FAIL_CODE;
        this.msg = msg;
        return this;
    }
    public Response<T>fail(){
        return fail(null);
    }
    public Response<T> msg(String code, String msg){
        this.code = code;
        this.msg = msg;
        return this;
    }
}
