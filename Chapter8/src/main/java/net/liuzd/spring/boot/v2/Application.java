package net.liuzd.spring.boot.v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;

@SpringBootApplication
@Order(value = 2)
/***
 * SpringBoot提供的一种简单的实现方案就是添加一个model并实现CommandLineRunner接口，实现功能的代码放在实现的run方法中
 * 如果有多个类实现CommandLineRunner接口，如何保证顺序 ?
 * 如果需要按照一定的顺序去执行，那么就需要在实体类上使用一个@Order注解（或者实现Order接口）来表明顺序
 */
public class Application implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("应用启动了...，开始执行其它事情");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }

}
