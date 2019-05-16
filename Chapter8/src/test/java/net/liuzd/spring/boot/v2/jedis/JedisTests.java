package net.liuzd.spring.boot.v2.jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
/**
 * JUnit4使用Java5中的注解（annotation），以下是JUnit4常用的几个annotation：
 * @Before：初始化方法 对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
 * @After：释放资源 对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）
 * @Test：测试方法，在这里可以测试期望异常和超时时间
 * @Test(expected=ArithmeticException.class)检查被测方法是否抛出ArithmeticException异常
 * ：忽略的测试方法
 * @BeforeClass：针对所有测试，只执行一次，且必须为static void
 * @AfterClass：针对所有测试，只执行一次，且必须为static void 一个JUnit4的单元测试用例执行顺序为：
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass; 每一个测试方法的调用顺序为：
 * @Before -> @Test -> @After;
 */
public class JedisTests {

    private static Jedis jedis;

    @BeforeClass
    public static void start() {
        // 连接本地的 Redis 服务
        if (null == jedis) {
            jedis = new Jedis("localhost");
            System.out.println("连接本地的 Redis 服务成功！");
        }
    }

    @AfterClass
    public static void end() {
        if (null != jedis) {
            jedis.close();
        }
        System.out.println("关闭本地的 Redis 服务成功！");
    }

    @Test    
    public void testStr() {
        System.out.println("---------------testStr--------------");
        // 设置 redis 字符串数据
        String key = "souvc";
        String url = "http://www.cnblogs.com/liuhongfeng/";
        jedis.del(key);
        jedis.set(key, url);
        // 获取存储的数据并输出
        String val = jedis.get(key);
        System.out.println("redis存储的字符串是: " + val);
        Assert.isTrue(url.equals(val), "not eq");
    }

    @Test    
    public void testList() {
        System.out.println("---------------testList--------------");
        // 存储数据到列表中
        String key = "kecheng";
        jedis.del(key);
        jedis.lpush(key, "java");
        jedis.lpush(key, "php");
        jedis.lpush(key, "Mysql");

        // 获取存储的数据并输出
        List<String> list = jedis.lrange(key, 0, 5);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("redis list里面存储的值是:" + list.get(i));
        }
    }

    @Test    
    public void testList2() {
        System.out.println("---------------testList2--------------");
        // 开始前，先移除所有的内容
        String key = "java framework";
        jedis.del(key);
        System.out.println(key + "=" + jedis.lrange(key, 0, -1));
        // 先向key java framework中存放三条数据
        jedis.lpush(key, "spring");
        jedis.lpush(key, "struts");
        jedis.lpush(key, "hibernate");

        // 再取出所有数据jedis.lrange是按范围取出，
        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange(key, 0, -1));

        jedis.del(key);
        jedis.rpush(key, "spring");
        jedis.rpush(key, "struts");
        jedis.rpush(key, "hibernate");
        System.out.println(jedis.lrange(key, 0, -1));
        //
    }

    @Test    
    public void testMap() {
        System.out.println("---------------testMap--------------");
        // -----添加数据----------
        Map<String, String> map = new HashMap<>();
        map.put("name", "xinxin");
        map.put("age", "22");
        map.put("qq", "123456");

        String key = "user";
        jedis.hmset(key, map);
        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
        List<String> rsmap = jedis.hmget(key, "name", "age", "qq");
        System.out.println("list>" + rsmap);

        // 删除map中的某个键值
        jedis.hdel(key, "age");
        System.out.println("age>" + jedis.hmget(key, "age")); // 因为删除了，所以返回的是null
        System.out.println(key + "1>" + jedis.hlen(key)); // 返回key为user的键中存放的值的个数2
        System.out.println(key + "2>" + jedis.exists(key));// 是否存在key为user的记录
                                                           // 返回true
        System.out.println(key + "3>" + jedis.hkeys(key));// 返回map对象中的所有key
        System.out.println(key + "4>" + jedis.hvals(key));// 返回map对象中的所有value

        Iterator<String> iter = jedis.hkeys(key).iterator();
        while (iter.hasNext()) {
            String mapkey = iter.next();
            System.out.println(mapkey + ":" + jedis.hmget(key, mapkey));
        }
    }

    @Test    
    public void testSet() {
        System.out.println("---------------testSet--------------");
        // 删除map中的某个键值
        String key = "userSet";
        if (jedis.exists(key)) {
            jedis.del(key);
        }
        // 添加
        jedis.sadd(key, "liuling");
        jedis.sadd(key, "xinxin");
        jedis.sadd(key, "ling");
        jedis.sadd(key, "zhangxinxin");
        jedis.sadd(key, "who");

        // 移除noname
        jedis.srem(key, "who");
        System.out.println("smembers>" + jedis.smembers(key));// 获取所有加入的value
        System.out.println("sismember>" + jedis.sismember(key, "who"));// 判断who是否是user集合的元素
         // 随机返回值
        System.out.println("srandmember>" + jedis.srandmember(key));
        System.out.println("scard>" + jedis.scard(key));// 返回集合的元素个数
    }

    @Test
    public void testSort() {
        System.out.println("---------------testSort--------------");
        // jedis 排序
        // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        String key = "sort";
        jedis.del(key);// 先清除数据，再加入数据进行测试
        jedis.rpush(key, "1");
        jedis.lpush(key, "6");
        jedis.lpush(key, "3");
        jedis.lpush(key, "9");
        System.out.println(jedis.lrange(key, 0, -1));// [9, 3, 6, 1]
        System.out.println(jedis.sort(key)); // [1, 3, 6, 9] //输入排序后结果
        System.out.println(jedis.lrange(key, 0, -1));
    }

}