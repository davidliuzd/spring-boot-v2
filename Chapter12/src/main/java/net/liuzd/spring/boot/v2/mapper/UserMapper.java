package net.liuzd.spring.boot.v2.mapper;

import net.liuzd.spring.boot.v2.entity.User;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:02
 */
public interface UserMapper  {

    User selectByPrimaryKey(long id);
}
