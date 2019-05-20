package net.liuzd.spring.boot.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.liuzd.spring.boot.v2.entity.SysUser;
import net.liuzd.spring.boot.v2.mapper.SysUserMapper;

@Service
public class SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    public SysUser findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }
}
