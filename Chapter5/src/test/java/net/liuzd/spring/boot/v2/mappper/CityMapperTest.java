package net.liuzd.spring.boot.v2.mappper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.liuzd.spring.boot.v2.domain.City;
import net.liuzd.spring.boot.v2.mapper.CityMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void selectCityByIdTest() {
        City city = cityMapper.selectCityById(1);
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }

}
