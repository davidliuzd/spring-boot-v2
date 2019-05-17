package net.liuzd.spring.boot.v2;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import net.liuzd.spring.boot.v2.domain.Person;
import net.liuzd.spring.boot.v2.repository.RxJava2PersonRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RxJava2PersonRepositoryTest {

    private final Logger            logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RxJava2PersonRepository rxJava2PersonRepository;

    @Test
    public void testAdd() throws Exception {
        // 创建多个User，并验证User总数
        int size = 20;
        List<Person> pps = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            Person p = new Person("liu" + i, "felix" + i, 22 + i);
            pps.add(p);
        }
        //
        Flowable<Person> f = rxJava2PersonRepository.saveAll(pps);
        this.logger.info(f.blockingFirst().toString());
    }

    @Test
    public void testDel() throws Exception {
        // 删除一个Person
        Person p = new Person("liu", "zidong", 22);
        Single<Person> s = rxJava2PersonRepository.save(p);
        Person person = rxJava2PersonRepository.findById(s.blockingGet().getId()).blockingGet();
        this.logger.info(person.toString());
        rxJava2PersonRepository.delete(person);
    }

    @Test
    public void testFindMore() throws Exception {
        //
        Flowable<Person> f_person = rxJava2PersonRepository.findByLastname("felix");
        this.logger.info("Flowable>" + f_person.toString());
        Maybe<Person> m_person = rxJava2PersonRepository.findByFirstnameAndLastname("liu0", "felix0");
        this.logger.info("Maybe>" + m_person.toString());
        //
        Single<String> lastname = null;
        f_person = rxJava2PersonRepository.findByLastname(lastname);
        this.logger.info("Single>" + f_person.toString());
        //
        m_person = rxJava2PersonRepository.findByFirstnameAndLastname("liu", "felix0");
        this.logger.info("Flux>" + m_person.toString());
        //
        f_person = rxJava2PersonRepository.findWithTailableCursorBy();
        this.logger.info("Flux>findWithTailableCursorBy>" + m_person.toString());
    }

}
