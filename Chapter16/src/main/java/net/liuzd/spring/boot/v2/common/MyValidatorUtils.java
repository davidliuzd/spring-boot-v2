package net.liuzd.spring.boot.v2.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * 工具类
 */
public class MyValidatorUtils {

    /**
     * 用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";
    /**
     * 密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 手机号
     */
    public static final String REGEX_MOBILE   = "^[1]([3-9][0-9]{1}|59|58|88|89)[0-9]{8}$";

    /**
     * 邮箱
     */
    public static final String REGEX_EMAIL    = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 汉字
     */
    public static final String REGEX_CHINESE  = "^[\\u4e00-\\u9fa5]+$";

    /**
     * 身份证
     */
    public static final String REGEX_ID_CARD  = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * URL
     */
    public static final String REGEX_URL      = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * IP地址
     */
    public static final String REGEX_IP_ADDR  = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    private static Validator   validator      = Validation.buildDefaultValidatorFactory().getValidator();

    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    public static boolean isContionChinese(String pin) {
        boolean isContionChinese = false;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(pin);      // p.matcher()只适合做
        while (m.find()) { // m.matches()全部匹配为true
            if (m.groupCount() >= 0) {
                isContionChinese = true;
                break;
            }
        }
        return isContionChinese;
    }

    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    public static <T> Map<String, String> validate(T obj) {
        Map<String, StringBuilder> errorMap = new HashMap<>();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() > 0) {
            String property = null;
            for (ConstraintViolation<T> cv : set) {
                // 这里循环获取错误信息，可以自定义格式
                property = cv.getPropertyPath().toString();
                if (errorMap.get(property) != null) {
                    errorMap.get(property).append(cv.getMessage());
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(cv.getMessage());
                    errorMap.put(property, sb);
                }
            }
        }
        return errorMap.entrySet().stream().collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().toString()));
    }

}