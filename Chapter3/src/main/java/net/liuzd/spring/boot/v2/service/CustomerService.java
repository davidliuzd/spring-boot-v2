package net.liuzd.spring.boot.v2.service;

import java.time.LocalDateTime;

public interface CustomerService {

    /**
     * 新增一个用户
     */
    void create(String firstName, LocalDateTime dateOfBirth);

    /**
     * 根据name删除
     * @param name
     */
    void deleteByName(String firstName);

    Integer counts();

    /**
     * 删除所有用户
     */
    void deleteAll();

}
