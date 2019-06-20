package net.liuzd.spring.boot.v2.common;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static <T> T getBean(@Nonnull Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    public static boolean contain(String name) {
        return ctx.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return ctx.isSingleton(name);
    }

}
