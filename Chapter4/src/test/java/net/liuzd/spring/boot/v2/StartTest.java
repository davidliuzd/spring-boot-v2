package net.liuzd.spring.boot.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.liuzd.spring.boot.v2.controller.CityControllerTest;
import net.liuzd.spring.boot.v2.service.CityRepositoryTests;
import net.liuzd.spring.boot.v2.service.HotelRepositoryTests;
import net.liuzd.spring.boot.v2.service.UserRepositoryTests;

@RunWith(Suite.class)  
@SuiteClasses({ 
    CityControllerTest.class,
    CityRepositoryTests.class,
    HotelRepositoryTests.class,
    UserRepositoryTests.class
})  
public class StartTest {

}
  