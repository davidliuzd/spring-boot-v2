package net.liuzd.spring.boot.v2.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.liuzd.spring.boot.v2.domain.User;
import net.liuzd.spring.boot.v2.mapper.UserMapper;
import net.liuzd.spring.boot.v2.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param map
     * @return
     */
    @Override
    public Object getList(Map<String, Object> map) {
        return userMapper.getList(map);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public int insert(String name, Integer age) {
        return userMapper.insert(name, age);
    }

}