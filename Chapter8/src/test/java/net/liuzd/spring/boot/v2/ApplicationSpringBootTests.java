package net.liuzd.spring.boot.v2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.domain.Project;
import net.liuzd.spring.boot.v2.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationSpringBootTests {

    @Autowired
    private StringRedisTemplate            template;

    @Autowired
    private RedisTemplate<String, User>    redisTemplate;

    @Autowired
    private RedisTemplate<String, Project> projectRedisTemplate;

    @Test
    public void testBasic() {
        ValueOperations<String, String> ops = this.template.opsForValue();
        String key = "spring.boot.redis.test";
        if (!this.template.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("Found key " + key + ", value=" + ops.get(key));

        // 保存字符串
        template.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", template.opsForValue().get("aaa"));
    }

    @Test
    public void testUser() {

        // 保存对象
        User user = new User("超人", 20);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蝙蝠侠", 30);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        user = new User("蜘蛛侠", 40);
        redisTemplate.opsForValue().set(user.getUsername(), user);

        Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
        Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
        Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

    }

    @Test
    public void testProject() {

        // 保存对象
        Project project = new Project("OA办公", 20);
        projectRedisTemplate.opsForValue().set(project.getName(), project);

        project = new Project("物流系统", 30);
        projectRedisTemplate.opsForValue().set(project.getName(), project);

        project = new Project("电商系统", 40);
        projectRedisTemplate.opsForValue().set(project.getName(), project);

        Assert.assertEquals(20, projectRedisTemplate.opsForValue().get("OA办公").getCounts());
        Assert.assertEquals(30, projectRedisTemplate.opsForValue().get("物流系统").getCounts());
        Assert.assertEquals(40, projectRedisTemplate.opsForValue().get("电商系统").getCounts());
    }

}