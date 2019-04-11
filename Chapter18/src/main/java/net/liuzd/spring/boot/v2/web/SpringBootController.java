package net.liuzd.spring.boot.v2.web;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.liuzd.spring.boot.v2.domain.SpringBoot;

@Controller
public class SpringBootController {
    
    @RequestMapping("spring-boots")
    public ModelAndView index(){      
        //
        ModelAndView modelAndView = new ModelAndView("/spring-boot");
        modelAndView.addObject("learnList", new ArrayList<SpringBoot>() {           
            private static final long serialVersionUID = 1L;
            {
                add(new SpringBoot("官方参考文档","Spring Boot Reference Guide","http://docs.spring.io/spring-boot/docs/1.5.1.RELEASE/reference/htmlsingle/#getting-started-first-application"));
                add(new SpringBoot("官方SpriongBoot例子","官方SpriongBoot例子","https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples"));
                add(new SpringBoot("龙国学院","Spring Boot 教程系列学习","http://www.roncoo.com/article/detail/125488"));
                add(new SpringBoot("嘟嘟MD独立博客","Spring Boot干货系列 ","http://tengj.top/"));
                add(new SpringBoot("后端编程嘟","Spring Boot教程和视频 ","http://www.toutiao.com/m1559096720023553/"));
                add(new SpringBoot("程序猿DD","Spring Boot系列","http://www.roncoo.com/article/detail/125488"));
                add(new SpringBoot("纯洁的微笑","Sping Boot系列文章","http://www.ityouknow.com/spring-boot"));
                add(new SpringBoot("CSDN——小当博客专栏","Sping Boot学习","http://blog.csdn.net/column/details/spring-boot.html"));
                add(new SpringBoot("梁桂钊的博客","Spring Boot 揭秘与实战","http://blog.csdn.net/column/details/spring-boot.html"));
                add(new SpringBoot("林祥纤博客系列","从零开始学Spring Boot ","http://412887952-qq-com.iteye.com/category/356333"));
            }
        });      
        return modelAndView;
    }
}
