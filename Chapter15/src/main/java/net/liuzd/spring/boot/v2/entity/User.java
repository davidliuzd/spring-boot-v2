package net.liuzd.spring.boot.v2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.liuzd.spring.boot.v2.common.BaseEntity;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;

/**
 * @since 2018-08-12
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String         name;

    private Integer        age;

    private String         email;

    /**
     * 数据库的值对应该注解对应的属性
     */
    private UserStatusEnum status;

}
