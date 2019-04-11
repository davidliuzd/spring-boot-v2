package net.liuzd.spring.boot.v2.web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.web.util.XssUtil;

@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        log.debug("request init ...");
    }

    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = cleanXSS("getParameter", parameter, values[i]);
        }
        return encodedValues;
    }

    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        if (value == null) {
            return null;
        }
        return cleanXSS("getParameter", parameter, value);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return cleanXSS("getHeader", name, value);
    }

    private String cleanXSS(String tag, String name, String value) {
        String xssValue = XssUtil.cleanXSS(value);
        log.debug("{}，name : {} ,value : {} ,xssValue : {}", tag, name, value, xssValue);
        return xssValue;
    }

}