package net.liuzd.spring.boot.v2;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.repository.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService.deleteAll();
    }

    @Test
    public void test() throws Exception {   
        // 插入5个用户
        customerService.create("a", LocalDateTime.now());
        customerService.create("b", LocalDateTime.now());
        customerService.create("c", LocalDateTime.now());
        customerService.create("d", LocalDateTime.now());
        customerService.create("e", LocalDateTime.now());

        // 查数据库，应该有5个用户
        Assert.assertEquals(5, customerService.counts().intValue());

        // 删除两个用户
        customerService.deleteByName("a");
        customerService.deleteByName("e");

        // 查数据库，应该有5个用户
        Assert.assertEquals(3, customerService.counts().intValue());

    }

}
