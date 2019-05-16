package net.liuzd.spring.boot.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.liuzd.spring.boot.v2.jedis.JedisTests;
import net.liuzd.spring.boot.v2.jedis.RedisTemplateTests;

@RunWith(Suite.class)  
@SuiteClasses({ 
    JedisTests.class,
    RedisTemplateTests.class
})  
public class StartTest {

}
  