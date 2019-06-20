package net.liuzd.spring.boot.v2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.Application;
import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;
import net.liuzd.spring.boot.v2.entity.query.UserQuery;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class UserServiceTest {

    @Resource
    private UserService userService;

    private User get(int i) {
        User bean = new User();
        bean.setName("原来你也在这里 " + i);
        bean.setAge(18 + i);
        bean.setEmail(i + "davidliuzd@sina.com");
        bean.setMark(1);
        bean.setStatus(UserStatusEnum.NORMAL);
        bean.setVersion(1);
        return bean;
    }

    @Test
    public void testInserts() {
        List<User> beans = new ArrayList<>();
        int size = 1;
        long s = System.nanoTime();
        for (int i = 0; i < size; i++) {
            User bean = get(i);
            userService.add(bean);
        }
        long e = System.nanoTime();
        log.info("size : " + beans.size() + "，insert counts : " + size + "，用时：" + (e - s));
        Assert.assertTrue("批量插入成功", size > 0);
    }

    @Test
    public void testInsert() {
        User bean = get(new Random().nextInt());
        Assert.assertNotNull(userService.add(bean));
        // 成功直接拿会写的 ID
        log.info("\n插入成功 ID 为：" + bean.getId());
    }

    @Test
    public void testFind() {
        User bean = userService.findById(1L);
        Assert.assertNotNull(bean);
        log.info("\n：" + bean);
    }

    @Test
    // @Ignore
    public void testPage() {
        UserQuery user = new UserQuery();
        List<User> beans = userService.page(user, 1, 10);
        log.info("PageInfo  = " + beans);
        Assert.assertNotNull(beans);
    }

    @Test
    public void delete() {
        User bean = get(new Random().nextInt());
        Assert.assertNotNull(userService.add(bean));
        Long id = bean.getId();
        userService.deleteById(id);
        bean = userService.findById(id);
        Assert.assertTrue(bean.getMark() == 0);
    }

    @Test
    public void update() {
        User bean = get(new Random().nextInt());
        Assert.assertNotNull(userService.add(bean));
        Long id = bean.getId();
        log.info("\n插入成功 ID 为：" + id);
        String name = "事故2";
        bean.setName(name);
        userService.update(bean);
        //
        User dbBean = userService.findById(id);
        Assert.assertEquals(name, dbBean.getName());
    }

}
