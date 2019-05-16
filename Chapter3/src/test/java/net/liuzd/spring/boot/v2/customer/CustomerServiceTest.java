package net.liuzd.spring.boot.v2.customer;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceTest {

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
        customerService.create("Meredith", LocalDateTime.now());
        customerService.create("Joan", LocalDateTime.now());

        Assert.assertEquals(7, customerService.counts().intValue());

        // 删除两个用户
        customerService.deleteByName("a");
        customerService.deleteByName("e");

        Assert.assertEquals(5, customerService.counts().intValue());

    }

}
