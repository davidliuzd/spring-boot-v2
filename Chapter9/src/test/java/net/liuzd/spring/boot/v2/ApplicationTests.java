package net.liuzd.spring.boot.v2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.domain.Customer;
import net.liuzd.spring.boot.v2.domain.User;
import net.liuzd.spring.boot.v2.repository.CustomerRepository;
import net.liuzd.spring.boot.v2.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UserRepository     userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void testCustomer() {

        // String name, int age, boolean active, String firstName, String
        // lastName
        this.customerRepository.save(new Customer("刘自东", 30, true, "刘", "自东"));
        this.customerRepository.save(new Customer("刘远", 18, false, "刘", "远"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : this.customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
    }

    @Test
    public void testUser() {

        // 创建三个User，并验证User总数
        userRepository.save(new User(1L, "didi", 30));
        userRepository.save(new User(2L, "mama", 40));
        userRepository.save(new User(3L, "kaka", 50));
        Assert.assertEquals(3, userRepository.findAll().size());

        // 删除一个User，再验证User总数
        User u = userRepository.findById(1L).get();
        userRepository.delete(u);
        Assert.assertEquals(2, userRepository.findAll().size());

        // 删除一个User，再验证User总数
        u = userRepository.findByUsername("mama");
        userRepository.delete(u);
        Assert.assertEquals(1, userRepository.findAll().size());
    }

}
