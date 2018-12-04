package net.liuzd.spring.boot.v2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.liuzd.spring.boot.v2.dao.CityDao;
import net.liuzd.spring.boot.v2.mapper.CityMapper;
import net.liuzd.spring.boot.v2.mapper.HotelMapper;

/**
 * 运行net.liuzd.spring.boot.v2.mappper包下的测试类这个注解得注释了
 * */
//@SpringBootApplication
public class MybatisApplicationTest implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplicationTest.class, args);
    }

    private final CityDao     cityDao;

    private final CityMapper  cityMapper;

    private final HotelMapper hotelMapper;

    public MybatisApplicationTest(CityDao cityDao, HotelMapper hotelMapper, CityMapper cityMapper) {
        this.cityDao = cityDao;
        this.hotelMapper = hotelMapper;
        this.cityMapper = cityMapper; 
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.cityDao.selectCityById(1));
        System.out.println(this.cityMapper.selectCityById(1));
        System.out.println(this.hotelMapper.selectByCityId(1));
    }

}