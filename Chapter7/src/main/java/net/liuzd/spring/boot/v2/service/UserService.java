package net.liuzd.spring.boot.v2.service;

import java.util.Map;

import net.liuzd.spring.boot.v2.domain.User;

/**
 * The Interface UserService.
 */
public interface UserService {

    Object getList(Map<String, Object> map);

    User findByName(String name);

    int insert(String name, Integer age);

}