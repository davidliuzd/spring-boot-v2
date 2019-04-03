package net.liuzd.spring.boot.v2.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import net.liuzd.spring.boot.v2.common.BaseEntity;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;

@Data
public class User extends BaseEntity{

    @NotBlank(message = "姓名不能为空！")
    @Size(max = 128, message = "姓名输入超长！")
    private String  name;

    @Size(max = 150, min = 18, message = "年龄在[18~150]之间")
    @NotBlank(message = "年龄不能为空！")
    private Integer age;
   
    @Email(message = "邮箱不正确！")
    @Size(max = 128, message = "邮箱输入超长！")
    private String  email;

    private UserStatusEnum status;

}