package net.liuzd.spring.boot.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author sgl
 * @Date 2018-06-11 17:21
 */
public interface SysPermissionMapper {
    List<String> selectPermissionByUserId(@Param("userId") long userId);
}
