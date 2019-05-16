package net.liuzd.spring.boot.v2.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.dao.CityRepository;
import net.liuzd.spring.boot.v2.dao.HotelRepository;
import net.liuzd.spring.boot.v2.domain.City;
import net.liuzd.spring.boot.v2.domain.Hotel;
import net.liuzd.spring.boot.v2.domain.enums.Rating;

/**
 * Integration tests for {@link HotelRepository}.
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelRepositoryTests {

    @Autowired
    CityRepository  cityRepository;

    @Autowired
    HotelRepository repository;

    @Test
    public void executesQueryMethodsCorrectly() {
        City city = this.cityRepository.findAll(PageRequest.of(0, 1, Direction.ASC, "name")).getContent().get(0);
        assertNotNull(city);
        Page<HotelSummary> hotels = this.repository.findByCity(city, PageRequest.of(0, 10, Direction.ASC, "name"));
        Hotel hotel = this.repository.findByCityAndName(city, hotels.getContent().get(0).getName());
        assertNotNull(hotel);
        List<RatingCount> counts = this.repository.findRatingCounts(hotel);
        assertNotNull(counts);
    }

}