package net.liuzd.spring.boot.v2.common;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    @SuppressWarnings("unused")
    private Boolean isJson(HttpServletRequest request) {
        String header = request.getHeader("content-type");
        return header != null && header.contains("json");
    }

}