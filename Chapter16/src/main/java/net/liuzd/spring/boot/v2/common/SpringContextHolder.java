package net.liuzd.spring.boot.v2.common;

import javax.annotation.Nonnull;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author shiyajian
 * create: 2018-12-27
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static Object getBean(@Nonnull Class clazz) {
        return ctx.getBean(clazz);
    }
}
