package net.liuzd.spring.boot.v2.web.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.liuzd.spring.boot.v2.config.ValidatorConfiguration;
import net.liuzd.spring.boot.v2.entity.User;

@RequestMapping("/validation")
@RestController
@Validated
public class ValidationController {

    /** 前面配置了快速失败返回的Bean */
    @Autowired
    private Validator validator;

    /**
     * 如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。
     * 使用@Valid注解，对RequestParam对应的参数进行注解，是无效的，需要使用@Validated注解来使得验证生效:MethodValidationPostProcessor的Bean
     * {@link ValidatorConfiguration.methodValidationPostProcessor}
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(
            @Range(min = 1, max = 9, message = "年级只能从1-9") @RequestParam(name = "grade", required = true) int grade,
            @Min(value = 1, message = "班级最小只能1") @Max(value = 99, message = "班级最大只能99") @RequestParam(name = "classroom", required = true) int classroom) {
        System.out.println(grade + "," + classroom);
    }

    @RequestMapping("/test2")
    public void test2() {
        User user = new User();
        user.setName("Spring Boot");
      /*  user.setAge(18);
        user.setBirthday("1990-11-23");
        user.setEmail("davidliuzd@sina.com");
        user.setIsFalse(false);
        user.setMobile("13745636836");*/
        Set<ConstraintViolation<User>> violationSet = validator.validate(user);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println(model.getMessage());
        }
    }
}