package net.liuzd.spring.boot.v2.config;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.liuzd.spring.boot.v2.config.enums.EnumConverterFactory;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer, WebBindingInitializer {

    /**
     * [get]请求中，将int值转换成枚举类
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

    /**
     * [GET]请求下，将所有参数的空格trim掉
     * 如果前台必须保留空格，前后空格请用%20转移
     * @param webDataBinder 数据绑定器
     */
    @Override
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
    
}