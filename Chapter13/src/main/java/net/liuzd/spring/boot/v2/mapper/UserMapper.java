package net.liuzd.spring.boot.v2.mapper;

import net.liuzd.spring.boot.v2.config.MyBaseMapper;
import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.model.UserPage;

/**
 * @author hubin
 * @since 2018-08-11
 */
public interface UserMapper extends MyBaseMapper<User> {

    /**
     * 自定义分页查询
     * @param userPage 单独 user 模块使用的分页
     * @return 分页数据
     */
    UserPage selectUserPage(UserPage userPage);
    
    Long saveOne(User user);

}
