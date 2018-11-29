package net.liuzd.spring.boot.v2.repository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @date 16/3/17 下午6:44.
 * @blog http://blog.didispace.com
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(String firstName, LocalDateTime dateOfBirth) {
        jdbcTemplate.update("insert into customer(FIRST_NAME, DATE_OF_BIRTH) values(?, ?)", firstName, dateOfBirth);
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete from customer where FIRST_NAME = ?", name);
    }

    @Override
    public Integer counts() {
        return jdbcTemplate.queryForObject("select count(1) from customer", Integer.class);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from customer");
    }
}
