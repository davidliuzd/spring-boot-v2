package net.liuzd.spring.boot.v2.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.dao.CityRepository;
import net.liuzd.spring.boot.v2.dao.HotelRepository;
import net.liuzd.spring.boot.v2.dao.ReviewRepository;
import net.liuzd.spring.boot.v2.domain.City;
import net.liuzd.spring.boot.v2.domain.Hotel;
import net.liuzd.spring.boot.v2.domain.Review;
import net.liuzd.spring.boot.v2.domain.dto.ReviewDetails;
import net.liuzd.spring.boot.v2.domain.enums.Rating;
import net.liuzd.spring.boot.v2.domain.enums.TripType;

/**
 * Integration tests for {@link CityRepository}.
 * @author Oliver Gierke
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryTests {

    @Autowired
    CityRepository   repository;

    @Autowired
    HotelRepository  hotelRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Before
    public void setUp() {
        reviewRepository.deleteAll();
        hotelRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void findAll() {
        //
        for (int i = 0; i < 12; i++) {
            City city = get(i);
            this.repository.save(city);
            Hotel hotel = new Hotel(city, "hotel" + i);
            hotel.setAddress("address" + i);
            hotel.setZip("zip" + i);
            Set<Review> rr = getReviews(hotel, i);
            hotel.setReviews(rr);
            hotelRepository.save(hotel);
            //
            reviewRepository.saveAll(rr);
            //
        }
        //
        Page<City> cities = this.repository.findAll(PageRequest.of(0, 10));
        assertThat(cities.getTotalElements()).isGreaterThan(1L);
    }

    private Set<Review> getReviews(Hotel hotel, int index) {
        Set<Review> rr = new HashSet<>();
        ReviewDetails rd = new ReviewDetails();
        rd.setCheckInDate(new Date());
        rd.setDetails("details" + index);
        rd.setTitle("title" + index);
        rd.setRating(Rating.values()[new Random().nextInt(Rating.values().length - 1)]);
        rd.setTripType(TripType.values()[new Random().nextInt(TripType.values().length - 1)]);
        //
        rr.add(new Review(hotel, index, rd));
        return rr;
    }

    private City get(int r) {
        return new City("name" + r, "state" + r, "country" + r, "map" + r);
    }

}