package net.liuzd.spring.boot.v2.util;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JSONUtils {

    public static final String       DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 使用全局的mapper 提高效率
     */
    public static final ObjectMapper mapper                  = new ObjectMapper();

    /**
     * 初始化mapper配置
     */
    static {

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, false);
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 这是时间格式化的格式
        mapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_FORMAT));
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    /**
     * 序列成JSON
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转化为json 字符串失败了!", e);
        }
        return json;
    }

    /**
     * @param obj
     * @return
     */
    public static String toPrettyJson(Object obj) {
        String json = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, false);
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            // 这是时间格式化的格式
            mapper.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_FORMAT));
            mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("转化json出错了...", e);
        }
        return json;
    }

    /**
     * @param <T>
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T read(String json, Class<T> clazz) {
        try {
            T t = mapper.readValue(json, clazz);
            return t;
        } catch (Exception e) {
            throw new RuntimeException("json转化为泛型" + clazz + "出错了...", e);
        }
    }

    /**
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T read(URL src, Class<T> clazz) {
        try {
            return (T) mapper.readValue(src, clazz);
        } catch (Exception e) {
            throw new RuntimeException("读取" + src + "转化为泛型" + clazz + "出错了...", e);
        }
    }

    /**
     * @param <T>
     * @param jsonText
     * @param collectionClass
     * @param classes
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T read(String jsonText, Class<T> collectionClass, Class<?>... classes) {
        try {
            JavaType javaType = getCollectionType(collectionClass, classes);
            return (T) mapper.readValue(jsonText, javaType);
        } catch (Exception e) {
            throw new RuntimeException("json转化集合" + collectionClass + "出错了... ，classes：" + classes
                    + " json ：" + jsonText, e);
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * @param jsonText
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Map read(String jsonText) {
        return read(jsonText, Map.class);
    }

    /**
     * 判断是否是json格式
     * @param str
     * @return
     */
    public static boolean isJson(String str) {
        if (!StringUtils.hasText(str)) {
            return str.startsWith("{");
        }
        return false;
    }

}
