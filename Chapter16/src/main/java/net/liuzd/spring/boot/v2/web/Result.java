package net.liuzd.spring.boot.v2.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Result implements Serializable {

    private static final long   serialVersionUID = 1L;
    private boolean             success;
    private String              code;
    private String              message;
    private Map<String, Object> data;

    public Result(final boolean isSuccess) {
        this.success = isSuccess;
        this.data = new HashMap<String, Object>();
    }

    public Result() {
        this(false);
    }

    public Result addResult(final String key, final Object value) {
        if (null == value) {
            return null;
        }
        this.getData().put(key, value);
        return this;
    }

    public Set<String> keySet() {
        return this.data.keySet();
    }

    public Object get(final String key) {
        return this.data.get(key);
    }

    public String getString(final String key) {
        final Object obj = this.data.get(key);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Result setSuccess(final boolean success) {
        this.success = success;
        return this;
    }

    public Result setSuccess() {
        return this.setSuccess(true);
    }

    public String getCode() {
        return this.code;
    }

    public Result setCode(final String code) {
        this.code = code;
        return this;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public Result setData(final Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result setMessage(final String message) {
        this.message = message;
        return this;
    }

}
