package net.liuzd.spring.boot.v2.mapper;

import net.liuzd.spring.boot.v2.entity.SysUser;


public interface SysUserMapper {
    SysUser findByUserName(String userName);
}
