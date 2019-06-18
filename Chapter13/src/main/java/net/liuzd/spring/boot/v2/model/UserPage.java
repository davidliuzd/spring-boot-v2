package net.liuzd.spring.boot.v2.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.liuzd.spring.boot.v2.entity.User;

/**
 * 自定义分页
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserPage extends Page<User> {
    
    private static final long serialVersionUID = 7246194974980132237L;

    private Integer selectInt;
    private String selectStr;

    public UserPage(long current, long size) {
        super(current, size);
    }
}
