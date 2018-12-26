package net.liuzd.spring.boot.v2.mapper;

import net.liuzd.spring.boot.v2.entity.SysUser;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-11 17:19
 */
public interface SysUserMapper {
    SysUser findByUserName(String userName);
}
