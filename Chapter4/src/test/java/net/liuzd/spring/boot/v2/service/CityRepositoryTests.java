package net.liuzd.spring.boot.v2.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.domain.City;

/**
 * Integration tests for {@link CityRepository}.
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTests {

    @Autowired
    CityRepository repository;

    @Before
    public void setUp() {
        repository.deleteAll();
    }

    @Test
    public void findAll() {

        for (int i = 0; i < 12; i++) {
            this.repository.save(get(i));
        }

        Page<City> cities = this.repository.findAll(PageRequest.of(0, 10));
        assertThat(cities.getTotalElements()).isGreaterThan(1L);
    }

    private City get(int r) {
        return new City("name" + r, "state" + r, "country" + r, "map" + r);
    }

}