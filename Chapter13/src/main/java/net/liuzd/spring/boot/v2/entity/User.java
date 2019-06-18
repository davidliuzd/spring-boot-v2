package net.liuzd.spring.boot.v2.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;

/**
 * 用户表 设置逻辑删除字段,并且逻辑删除字段不 select 出来
 */
@TableName(value = "user")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {    

    private String         name;

    private Integer        age;

    @TableField(el = "email, typeHandler=net.liuzd.spring.boot.v2.config.UserEmailTypeHandler")
    private String         email;
  
    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private UserStatusEnum status;

}
