package net.liuzd.spring.boot.v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.mapper.UserMapper;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:01
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User selectById(long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
