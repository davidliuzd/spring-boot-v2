package net.liuzd.spring.boot.v2.mapper;
import org.apache.ibatis.annotations.Mapper;

import net.liuzd.spring.boot.v2.domain.Hotel;

/**
 * @author Eduardo Macarron
 */
@Mapper
public interface HotelMapper {

	Hotel selectByCityId(int city_id);

}