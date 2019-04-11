package net.liuzd.spring.boot.v2.web.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.util.HtmlUtils;

public class XssUtil {

    public static final List<Pattern> FILTER_PATTERNS = Collections.unmodifiableList(Arrays.<Pattern> asList(

            // Avoid common html tags
            Pattern.compile("(<input(.*?)></input>|<input(.*)/>)", Pattern.CASE_INSENSITIVE), Pattern.compile(
                    "<span(.*?)</span>", Pattern.CASE_INSENSITIVE), Pattern.compile("<div(.*)</div>",
                            Pattern.CASE_INSENSITIVE), Pattern.compile("<style>(.*?)</style>",
                                    Pattern.CASE_INSENSITIVE),
            // Avoid onload= expressions
            Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid anything between script tags
            Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
            // Avoid javascript:... expressions
            Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
            // Remove any lonesome </script> tag
            Pattern.compile("</script>", Pattern.CASE_INSENSITIVE), Pattern.compile("<script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid anything in a src='...' type of expression
            Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
                    | Pattern.DOTALL), Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
                            | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid eval(...) expressions
            Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL), Pattern
                    .compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
            // Avoid vbscript:... expressions
            Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
            //
            Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\"]")
    //

    ));

    public static final String        EMPTY           = "";

    /**
     * @Title: cleanXSS
     * @Description: You'll need to remove the spaces from the html entities
     *               below
     * @param @param value
     * @param @return
     * @return String
     */
    public static String cleanXSS(String value) {
        // 第一种
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
       // value = value.replaceAll("script", "");
        // 第二种
        /*
         * if (value != null) { for (Pattern pattern : FILTER_PATTERNS) { value
         * = pattern.matcher(value).replaceAll(EMPTY).trim(); } }
         */
        // 第三种
        // HtmlUtils.htmlEscape(value);过滤掉特殊字符，不进行转义
        // 4
        // Pattern pattern =
        // Pattern.compile("[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\"]");
        // value = pattern.matcher(value).replaceAll(EMPTY).trim();
        //
        return value;
    }

    public static boolean handleExcludeURL(List<String> excludes, String url) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

}
