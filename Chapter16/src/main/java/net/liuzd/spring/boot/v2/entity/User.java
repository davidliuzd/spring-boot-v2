package net.liuzd.spring.boot.v2.entity;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class User {    
    
    private long id;

    @NotBlank(message = "姓名不能为空！")
    @Size(max = 128, message = "姓名输入超长！")
    private String   name;
  
    @NotBlank(message = "手机号不能为空！")
    @Size(max = 11, message = "手机号输入超长！")
    private String   mobile;

    @Range(min = 18, max = 150, message = "年龄在[18~150]之间")
    @NotBlank(message = "年龄不能为空！")
    private Integer  age;

    @Email(message = "邮箱不正确！")
    @Size(max = 128, min = 0, message = "邮箱输入超长！")
    private String   email;

    @AssertFalse(message = "必须为false")
    private Boolean  isFalse;
    /**
     * 如果是空，则不校验，如果不为空，则校验
     */
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "出生日期格式不正确")
    private String   birthday;

}