package net.liuzd.spring.boot.v2.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.liuzd.spring.boot.v2.domain.City;
import net.liuzd.spring.boot.v2.domain.dto.CitySearchCriteria;

public interface CityService {

	Page<City> findCities(CitySearchCriteria criteria, Pageable pageable);

	City getCity(String name, String country);

	Page<HotelSummary> getHotels(City city, Pageable pageable);

}