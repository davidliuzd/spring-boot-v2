package net.liuzd.spring.boot.v2.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import lombok.extern.log4j.Log4j2;
import net.liuzd.spring.boot.v2.common.Callback;
import net.liuzd.spring.boot.v2.web.Result;

@Log4j2
public class ResultUtils {    

    public static <T> Result get(String key, Callback<T> callBack) {
        Result result = new Result(true);
        try {
            T t = callBack.doCallback();
            result.addResult(key, t);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("获取数据出错了...", e);
        }
        return result;
    }

    public static <T> Result get(Callback<Boolean> callBack) {
        Result result = new Result(true);
        try {
            result.setSuccess(callBack.doCallback());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("获取数据出错了...", e);
        }
        return result;
    }

    public static Result getResult(BindingResult bindingResult) {
        Result result = new Result(true);
        if (bindingResult.hasErrors()) {
            result.setSuccess(false);
            StringBuffer errorMsgs = new StringBuffer();
            for (ObjectError error : bindingResult.getAllErrors()) {
                errorMsgs.append(error.getDefaultMessage()).append(" ");
            }
            result.setMessage(errorMsgs.toString());
        }
        return result;
    }

}
