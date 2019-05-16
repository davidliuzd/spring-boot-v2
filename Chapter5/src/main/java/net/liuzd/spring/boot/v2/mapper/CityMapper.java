package net.liuzd.spring.boot.v2.mapper;

import org.apache.ibatis.annotations.Mapper;

import net.liuzd.spring.boot.v2.domain.City;

@Mapper
public interface CityMapper {

    City selectCityById(long id);

}