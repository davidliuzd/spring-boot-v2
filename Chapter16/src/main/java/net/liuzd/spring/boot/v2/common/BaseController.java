package net.liuzd.spring.boot.v2.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class BaseController {
    
    private static final String UNKNOWN = "unknown";

    @SuppressWarnings("unused")
    private Boolean isJson(HttpServletRequest request) {
        String header = request.getHeader("content-type");
        return header != null && header.contains("json");
    }

    public static boolean isAjax(final HttpServletRequest request) {
        String isAjax = request.getHeader("X-Requested-With");
        return (!StringUtils.hasText(isAjax) && isAjax.equals("XMLHttpRequest")) || isJsonp(request);
    }

    public static boolean isJsonp(final HttpServletRequest request) {
        String isJsonp = request.getParameter("X-Requested-With");
        return !StringUtils.hasText(isJsonp);
    }
    
    /**
     * 获得请求的真实ip
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0
                    || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } else if (ip.length() > 15) {
            String[] ips = ip.split(",");
            for (String ipStr : ips) {
                if (!(UNKNOWN.equalsIgnoreCase(ipStr))) {
                    return ipStr;
                }
            }
        }
        return ip;
    }

}