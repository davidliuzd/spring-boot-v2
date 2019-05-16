package net.liuzd.spring.boot.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import net.liuzd.spring.boot.v2.mappper.CityMapperTest;
import net.liuzd.spring.boot.v2.mappper.HotelMapperTest;
import net.liuzd.spring.boot.v2.mappper.UserMapperTest;

@RunWith(Suite.class)  
@SuiteClasses({ 
    CityMapperTest.class,
    HotelMapperTest.class,
    UserMapperTest.class
})  
public class StartTest {

}
  