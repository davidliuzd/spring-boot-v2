package net.liuzd.spring.boot.v2.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.query.UserQuery;
import net.liuzd.spring.boot.v2.mapper.UserMapper;

@Service
public class UserService {

    @Autowired
    private UserMapper mappper;

    public Serializable add(User bean) {
        return mappper.insert(bean);
    }

    public int update(User bean) {
        return mappper.update(bean);
    }

    public User findById(Serializable id) {
        return mappper.findById(id);
    }

    public int deleteById(Serializable id) {
        return mappper.deleteById(id);
    }

    public List<User> page(UserQuery query, Integer page, Integer pageSize) {
        query.setMark(1);
        PageHelper.offsetPage((page - 1) * pageSize, pageSize);
        PageHelper.orderBy("id desc");
        return mappper.query(query);
    }

}
