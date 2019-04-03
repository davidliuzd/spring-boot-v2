package net.liuzd.spring.boot.v2.mapper;

import java.io.Serializable;
import java.util.List;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.query.UserQuery;


public interface UserMapper {

    public Serializable insert(User bean);

    public int update(User bean);

    public User findById(Serializable id);

    public int deleteById(Serializable id);

    public List<User> query(UserQuery query);

}
