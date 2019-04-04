package net.liuzd.spring.boot.v2.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class HttpUtils {

    public static final String                              ENCODE_UTF8 = "UTF-8";

    private static final PoolingHttpClientConnectionManager PHCCM       = new PoolingHttpClientConnectionManager();
    private static final CloseableHttpClient                DEFAULT_HTTP_CLIENT;
    //
    static {
        PHCCM.setMaxTotal(200);
        PHCCM.setDefaultMaxPerRoute(200);
        DEFAULT_HTTP_CLIENT = HttpClients.custom().setConnectionManager(PHCCM).build();
    }

    private static CloseableHttpClient getHttpClient() {
        return DEFAULT_HTTP_CLIENT;
    }

    public static String doGet(String url) {
        return doGet(url, null, 5000);
    }

    public static String doGet(String url, Map<String, Object> paramMap) {
        return doGet(url, paramMap, 5000);
    }

    public static String doGet(String url, Map<String, Object> paramMap, int connectionTimeout) {
        //
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        List<NameValuePair> pairs = getNameValuePair(paramMap);
        ub.setParameters(pairs);
        try {
            HttpGet httpGet = new HttpGet(ub.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(5000).build();
            httpGet.setConfig(requestConfig);
            return getResult(httpGet);
        } catch (Exception e) {
            log.error("httpClient send fail, url = " + url, e);
        }
        return null;
    }

    private static List<NameValuePair> getNameValuePair(Map<String, Object> params) {
        //
        List<NameValuePair> naps = new ArrayList<>();
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                naps.add(new BasicNameValuePair((String) param.getKey(), String.valueOf(param.getValue())));
            }
        }
        //
        return naps;
    }

    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
                response.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            log.error("HttpUtils getResult error", e);
        } catch (IOException e) {
            log.error("HttpUtils getResult error", e);
        }
        return null;
    }

    public static boolean isAjax(HttpServletRequest request) {
        String isAjax = request.getHeader("X-Requested-With");
        if (StringUtils.equals(isAjax, "XMLHttpRequest")) {
            return true;
        }
        return isJsonp(request);
    }

    public static boolean isJsonp(HttpServletRequest request) {
        String isJsonp = request.getParameter("X-Requested-With");
        return StringUtils.isNotEmpty(isJsonp);
    }

    @SuppressWarnings("unused")
    private Boolean isJson(HttpServletRequest request) {
        String header = request.getHeader("content-type");
        return StringUtils.contains(header, "json");
    }

}