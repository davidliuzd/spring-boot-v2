package net.liuzd.spring.boot.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.liuzd.spring.boot.v2.customer.CustomerControllerTest;
import net.liuzd.spring.boot.v2.customer.CustomerRepositoryTest;
import net.liuzd.spring.boot.v2.customer.CustomerServiceTest;

@RunWith(Suite.class)  
@SuiteClasses({ 
    CustomerControllerTest.class,
    CustomerRepositoryTest.class,
    CustomerServiceTest.class,
})  
public class StartTest {

}
  