package net.liuzd.spring.boot.v2.mappper;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageInfo;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;
import net.liuzd.spring.boot.v2.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
// @Transactional 注释打开会自动恢复初始数据（清除插入与更新的数据）
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testInserts() {
        List<User> users = new ArrayList<>();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            User user = new User();
            user.setName("原来你也在这里" + i);
            user.setAge(18 + i);
            user.setEmail(i + "davidliuzd@sina.com");
            user.setStatus(UserStatusEnum.NORMAL);
            user.setMark(0);
            user.setVersion(0);
            users.add(user);
        }
        long s = System.nanoTime();
        int counts = userService.inserts(users);
        long e = System.nanoTime();
        System.out.println("users size : " + users.size() + "，insert counts : " + counts + "，用时：" + (e - s));
        Assert.assertTrue("批量插入成功", counts > 0);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("原来你也在这里");
        user.setAge(18);
        user.setEmail("davidliuzd@sina.com");
        user.setStatus(UserStatusEnum.NORMAL);
        user.setMark(0);
        user.setVersion(0);
        Assert.assertTrue(userService.save(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }

    @Test
    public void testPage() {
        User user = new User();
        List<User> users = userService.getAll(user);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        System.out.println("PageInfo  = " + pageInfo);
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    @Test
    public void delete() {
        // 更新字段：deleted 为1
        Long id = 3L;
        userService.deleteById(id);
        Assert.assertNull(userService.getById(id));
    }

    @Test
    public void update() {
        User user = new User();
        user.setName("原来你也在这里");
        user.setAge(18);
        user.setEmail("davidliuzd@sina.com");
        user.setStatus(UserStatusEnum.NORMAL);
        user.setMark(0);
        user.setVersion(0);
        Assert.assertTrue(userService.save(user) > 0);
        // 成功直接拿会写的 ID
        Long id = user.getId();
        System.err.println("\n插入成功 ID 为：" + id);
        //
        int age = 20;
        user.setAge(age);
        userService.save(user);
        //
        user = userService.getById(id);
        Assert.assertTrue(age == user.getAge());
    }

}