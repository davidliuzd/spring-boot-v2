package net.liuzd.spring.boot.v2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.domain.Person;
import net.liuzd.spring.boot.v2.repository.ReactivePersonRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReactivePersonRepositoryTest {

    private final Logger             logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReactivePersonRepository reactivePersonRepository;

    @Test
    public void testAdd() throws Exception {
        // ReactiveMongoTemplate t = null;
        // 创建多个User，并验证User总数
        int size = 20;
        for (int i = 0; i < size; i++) {
            reactivePersonRepository.save(new Person("liu" + i, "felix" + i, 22 + i));
        }
    }

    @Test
    public void testDel() throws Exception {
        // 删除一个Person
        Person p = new Person("liu", "zidong", 22);
        Mono<Person> m = reactivePersonRepository.save(p);
        Flux<Person> f_person = reactivePersonRepository.findByLastname(m.block().getLastname());
        this.logger.info(f_person.toString());
        reactivePersonRepository.delete(f_person.blockFirst());
    }

    @Test
    public void testFindMore() throws Exception {
        //
        Flux<Person> f_person = reactivePersonRepository.findByLastname("felix");
        this.logger.info("Flux>" + f_person.toString());
        Mono<Person> m_person = reactivePersonRepository.findByFirstnameAndLastname("liu0", "felix0");
        this.logger.info("Mono>" + m_person.toString());
        // Flux<Person> findByLastname(Mono<String> lastname);
        Mono<String> lastname = Mono.empty();
        f_person = reactivePersonRepository.findByLastname(lastname);
        this.logger.info("Flux>Mono>" + f_person.toString());
        // Mono<Person> findByFirstnameAndLastname(Mono<String> firstname,
        // String lastname);
        m_person = reactivePersonRepository.findByFirstnameAndLastname(Mono.empty(), "felix0");
        this.logger.info("Flux>Mono>" + m_person.toString());
        // Flux<Person> findWithTailableCursorBy();
        f_person = reactivePersonRepository.findWithTailableCursorBy();
        this.logger.info("Flux>findWithTailableCursorBy>" + m_person.toString());
    }

}
