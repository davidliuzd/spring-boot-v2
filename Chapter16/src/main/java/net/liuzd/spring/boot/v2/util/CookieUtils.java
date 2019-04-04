package net.liuzd.spring.boot.v2.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {

    private final static String UTF_8 = "UTF-8";

    public static String getCookieValue(HttpServletRequest request, String name) {
        return getCookieValue(request, name, UTF_8);
    }

    public static String getCookieValue(HttpServletRequest request, String name, String charset) {
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(name)) {
                    cookieValue = cookie.getValue();
                    if (StringUtils.isEmpty(cookieValue)) {
                        continue;
                    }
                    try {
                        cookieValue = URLDecoder.decode(cookieValue, charset);
                    } catch (UnsupportedEncodingException e) {
                        cookieValue = null;
                    }
                }
            }
        }
        return cookieValue;
    }

    public void deleteCookie(HttpServletResponse servletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        servletResponse.addCookie(cookie);
    }
}