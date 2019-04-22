package net.liuzd.spring.boot.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 14:51
 */
@SpringBootApplication
public class Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("所以beanNames个数：" + beanNames.length);
        for (String bn : beanNames) {
            System.out.println("bean name : " + bn);

        }
    }

    /**
     * 方法一、覆盖方法configureMessageConverters，使用fastJson， 此处把@Bean注释掉是为了测试方式二
     * @return
     */
    @Bean
    /**
     * 通过resources.web.fastjson来决定是否使用：fastjson，反之Jackson
     * */
    @ConditionalOnExpression("#{${resources.web.fastjson}==true}")
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }

    /*
     * @Override public void
     * configureMessageConverters(List<HttpMessageConverter<?>> converters) {
     * //1、定义一个convert转换消息的对象 FastJsonHttpMessageConverter fastConverter = new
     * FastJsonHttpMessageConverter(); //2、添加fastjson的配置信息 FastJsonConfig
     * fastJsonConfig = new FastJsonConfig();
     * fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
     * //3、在convert中添加配置信息 fastConverter.setFastJsonConfig(fastJsonConfig);
     * //4、将convert添加到converters中 converters.add(fastConverter); }
     */
}
