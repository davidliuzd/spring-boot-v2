package net.liuzd.spring.boot.v2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.domain.User;
import net.liuzd.spring.boot.v2.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testAdd() throws Exception {
        // 创建多个User，并验证User总数
        userRepository.save(new User(1L, "felix", 22));
        userRepository.save(new User(2L, "tim", 23));
        userRepository.save(new User(3L, "cc", 23));    
        userRepository.save(new User(4L, "pyh", 22));
        userRepository.save(new User(5L, "simba", 28));
        userRepository.save(new User(6L, "felix2", 10));
        userRepository.save(new User(7L, "felix3", 20));
        userRepository.save(new User(8L, "felixfelix", 30));
        userRepository.save(new User(9L, "felix4", 40));
        userRepository.save(new User(10L, "felix5", 50));
        userRepository.save(new User(11L, "felix6", 60));
        userRepository.save(new User(12L, "felix7", 70));
        userRepository.save(new User(13L, "felix8", 80));
        userRepository.save(new User(14L, "felix9", 90));
        userRepository.save(new User(15L, "felix10", 100));
        userRepository.save(new User(16L, "", 50));
        userRepository.save(new User(17L, null, 50));
    }
    
    @Test
    public void testDel() throws Exception {
        // 删除一个User，再验证User总数
        User u = userRepository.findById(1L).orElse(null);
        this.logger.info(u.toString());
        userRepository.delete(u);
    }
    
    @Test
    public void testFindMore() throws Exception {

        List<User> u1 = userRepository.findByUserNameLike("felix");
        this.logger.info(u1.toString());
        List<User> u2 = userRepository.findByUserName("felix");
        this.logger.info(u2.toString());
        List<User> u3 = userRepository.findByAgeGreaterThan(40);
        this.logger.info(u3.toString());
        List<User> u4 = userRepository.findByAgeLessThan(40);
        this.logger.info(u4.toString());
        List<User> u5 = userRepository.findByAgeBetween(20, 30);
        this.logger.info(u5.toString());
        List<User> u6 = userRepository.findByUserNameNotNull();
        this.logger.info(u6.toString());
        List<User> u7 = userRepository.findByUserNameNull();
        this.logger.info(u7.toString());
        List<User> u8 = userRepository.findByUserNameNot("felix");
        this.logger.info(u8.toString());

    }
    
    @Test
    public void test3() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        Page<User> u1 = userRepository.findByNameAndAgeRange("felix", 50, pageable);
        this.logger.info(u1.toString());
        u1.getContent().stream().forEach(n -> System.out.println(n.toString()));
        Page<User> u2 = userRepository.findByNameAndAgeRange2("felix", 0, 50, pageable);
        this.logger.info(u2.toString());
        u2.getContent().stream().forEach(n -> System.out.println(n.toString()));
        Page<User> u3 = userRepository.findByNameAndAgeRange3("felix", 0, 50, pageable);
        this.logger.info(u3.toString());
        u3.getContent().stream().forEach(n -> System.out.println(n.toString()));
    }


}
