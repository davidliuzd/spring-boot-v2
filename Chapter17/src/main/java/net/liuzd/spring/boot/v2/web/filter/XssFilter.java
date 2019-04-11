package net.liuzd.spring.boot.v2.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.web.util.XssUtil;

@Slf4j
public class XssFilter implements Filter {

    private List<String> excludes = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludesStr = filterConfig.getInitParameter("excludes");
        if (null != excludesStr && excludesStr.length() > 0) {
            excludes.addAll(Arrays.asList(excludesStr.split(",")));
        }
        log.debug("(XssFilter) initialize");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        log.debug("(XssFilter) doFilter");
        //
        HttpServletRequest req = (HttpServletRequest) request;
        if (XssUtil.handleExcludeURL(excludes, req.getServletPath())) {
            log.debug("排除请求url...");
            chain.doFilter(request, response);
            return;
        }
        //
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(req);
        chain.doFilter(xssRequest, response);
    }

    @Override
    public void destroy() {
        log.debug("(XssFilter) destroy");
    }

}