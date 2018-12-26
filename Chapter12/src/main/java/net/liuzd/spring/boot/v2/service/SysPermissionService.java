package net.liuzd.spring.boot.v2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.liuzd.spring.boot.v2.mapper.SysPermissionMapper;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-11 17:10
 */
@Service
public class SysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    public List<String> selectPermissionByUserId(long userId) {
        return sysPermissionMapper.selectPermissionByUserId(userId);
    }
}
