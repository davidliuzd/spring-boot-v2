package net.liuzd.spring.boot.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface SysPermissionMapper {
    List<String> selectPermissionByUserId(@Param("userId") long userId);
}
