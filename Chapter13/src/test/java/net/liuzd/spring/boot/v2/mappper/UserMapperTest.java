package net.liuzd.spring.boot.v2.mappper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import net.liuzd.spring.boot.v2.entity.User;
import net.liuzd.spring.boot.v2.entity.enums.UserStatusEnum;
import net.liuzd.spring.boot.v2.mapper.UserMapper;
import net.liuzd.spring.boot.v2.model.UserPage;

@RunWith(SpringJUnit4ClassRunner.class)
// @Transactional 注释打开会自动恢复初始数据（清除插入与更新的数据）
@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper         mapper;

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void testPage() {
        System.out.println("------ 自定义 xml 分页 ------");
        // UserPage selectPage = new UserPage(1, 5).setSelectInt(20);
        UserPage selectPage = new UserPage(1, 5);
        UserPage userPage = mapper.selectUserPage(selectPage);
        Assert.assertSame(userPage, selectPage);
        System.out.println("总条数 ------> " + userPage.getTotal());
        System.out.println("当前页数 ------> " + userPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userPage.getSize());
        print(userPage.getRecords());

        System.out.println("------ baseMapper 自带分页 ------");
        Page<User> page = new Page<>(1, 5);
        IPage<User> userIPage = mapper.selectPage(page, new QueryWrapper<User>().eq("age", 20));
        Assert.assertSame(userIPage, page);
        System.out.println("总条数 ------> " + userIPage.getTotal());
        System.out.println("当前页数 ------> " + userIPage.getCurrent());
        System.out.println("当前每页显示数 ------> " + userIPage.getSize());
        print(userIPage.getRecords());
    }

    int size = 10001;

    @Test
    public void testInserts() {
        List<User> users = new ArrayList<>();
        int bath = 200;
        int counts = 0;
        for (int i = 0; i < size; i++) {
            User user = getUser(i);
            users.add(user);
            if (users.size() == bath) {
                counts += mapper.insertBatch(users);
                users.clear();
            }
        }
        long s = System.nanoTime();
        if (users.size() > 0) {
            counts += mapper.insertBatch(users);
        }
        long e = System.nanoTime();
        // 100条> 37343029
        // 200条> 34796082
        // 500条 > 33109665
        // 1000条>61176433
        // 根据实际场景来，如大数据量使用方法三，关键是测试mbatis的批量执行最大边界
        System.out.println("users size : " + users.size() + "，insert counts : " + counts + "，用时：" + (e - s));
        Assert.assertTrue("批量插入成功", counts > 0);
    }

    private User getUser(int i) {
        User user = new User();
        user.setName("原来你也在这里" + i);
        user.setAge(18 + i);
        user.setVersion(i);
        user.setEmail(i + "davidliuzd@sina.com");
        user.setStatus(UserStatusEnum.NORMAL);
        user.setMark(0);
        return user;
    }

    @Test
    public void testInserts2() {
        long s = System.nanoTime();
        int counts = 0;
        for (int i = 0; i < size; i++) {
            User user = getUser(i);
            Assert.assertTrue(mapper.insert(user) > 0);
            counts++;
        }
        long e = System.nanoTime();
        // 366225347844
        System.out.println("users size : " + size + "，insert counts : " + counts + "，用时：" + (e - s));
        Assert.assertTrue("批量插入成功", counts > 0);
    }

    @Test
    public void testInserts3() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            User user = getUser(i);
            users.add(user);
        }
        long s = System.nanoTime();
        int result = 1;
        SqlSession batchSqlSession = null;
        try {
            batchSqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);// 获取批量方式的sqlsession
            int batchCount = 1000;// 每批commit的个数
            int batchLastIndex = batchCount;// 每批最后一个的下标
            String statement = "net.liuzd.spring.boot.v2.mapper.UserMapper.insertBatch";
            for (int index = 0; index < size;) {
                if (batchLastIndex >= size) {
                    batchLastIndex = size;
                    result = result * batchSqlSession.insert(statement, users.subList(index, batchLastIndex));
                    batchSqlSession.commit();
                    System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
                    break;// 数据插入完毕，退出循环
                } else {
                    result = result * batchSqlSession.insert(statement, users.subList(index, batchLastIndex));
                    batchSqlSession.commit();
                    System.out.println("index:" + index + " batchLastIndex:" + batchLastIndex);
                    index = batchLastIndex;// 设置下一批下标
                    batchLastIndex = index + (batchCount - 1);
                }
            }
            batchSqlSession.commit();
        } finally {
            batchSqlSession.close();
        }
        long e = System.nanoTime();
        // 6007770095
        System.out.println("users size : " + users.size() + "，insert counts : " + size + "，用时：" + (e - s));
    }

    @Test
    public void testInsert() {
        User user = getUser(new Random().nextInt(100));
        Assert.assertTrue(mapper.insert(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }

    @Test
    public void testSaveOne() {
        User user = getUser(new Random().nextInt(100));
        Assert.assertTrue(mapper.saveOne(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());
    }

    @Test
    public void testSelect() {
        System.out.println(mapper.selectById(1L));
    }

    @Test
    public void testDelAll() {
        mapper.deleteAll();
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

    @Test
    public void bDelete() {
        // 更新字段：deleted 为1
        Assert.assertTrue(mapper.deleteById(3L) > 0);
        Assert.assertTrue(mapper.delete(new QueryWrapper<User>().lambda().eq(User::getName, "Jack")) > 0);
    }

    @Test
    public void cUpdate() {
        Assert.assertTrue(mapper.update(new User().setName("Jack"), new UpdateWrapper<User>().lambda().set(User::getAge,
                3).eq(User::getId, 2)) > 0);
    }

    @Test
    public void dSelect() {
        Assert.assertEquals("test1@baomidou.com", mapper.selectById(1L).getEmail());
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
        Assert.assertEquals("Jack", user.getName());
        Assert.assertTrue(22 == user.getAge());
    }

    /**
     * @author 2019年3月19日 上午11:38:49
     * @Title: testUpdateByIdSucc
     * @Description:乐观锁 void
     */
    @Test
    public void testUpdateByIdSucc() {
        User user = new User();
        user.setAge(18);
        user.setEmail("test@baomidou.com");
        user.setName("optlocker");
        user.setVersion(1);
        user.setStatus(UserStatusEnum.NORMAL);
        mapper.insert(user);
        Long id = user.getId();

        User userUpdate = new User();
        userUpdate.setId(id);
        userUpdate.setAge(19);
        userUpdate.setVersion(1);
        Assert.assertEquals("Should update success", 1, mapper.updateById(userUpdate));
        Assert.assertEquals("Should version = version+1", 2, userUpdate.getVersion().intValue());
    }
}