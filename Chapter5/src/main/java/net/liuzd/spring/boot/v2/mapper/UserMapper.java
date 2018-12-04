package net.liuzd.spring.boot.v2.mapper;

import org.apache.ibatis.annotations.*;

import net.liuzd.spring.boot.v2.domain.User;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

}