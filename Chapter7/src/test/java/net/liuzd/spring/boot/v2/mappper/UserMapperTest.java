package net.liuzd.spring.boot.v2.mappper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.liuzd.spring.boot.v2.domain.User;
import net.liuzd.spring.boot.v2.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback
    public void findByName() throws Exception {
        userMapper.insert("AAA", 200);
        User u = userMapper.findByName("AAA");
        Assert.assertEquals(200, u.getAge().intValue());
    }

}