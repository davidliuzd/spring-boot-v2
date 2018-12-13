package net.liuzd.spring.boot.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/**
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux
 * https://www.imooc.com/article/78944?block_id=tuijian_wz
 * https://www.jianshu.com/p/55c1a84bd145
 * http://kplxq.com/2018/04/03/SpringBoot2-0%E4%B9%8BWebFlux%E5%BC%80%E5%8F%91%E5%AE%9E%E6%88%98%EF%BC%88%E5%90%AB%E6%BA%90%E7%A0%81%EF%BC%89/
 * https://www.ibm.com/developerworks/cn/java/spring5-webflux-reactive/index.html
 */
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        return new TomcatServletWebServerFactory(80);
    }

}
