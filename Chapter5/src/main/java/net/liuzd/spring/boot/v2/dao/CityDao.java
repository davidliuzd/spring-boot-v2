package net.liuzd.spring.boot.v2.dao;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import net.liuzd.spring.boot.v2.domain.City;

/**
 * @author Eddú Meléndez
 */
@Component
public class CityDao {

	private final SqlSession sqlSession;

	public CityDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public City selectCityById(long id) {
		return this.sqlSession.selectOne("selectCityById", id);
	}

}