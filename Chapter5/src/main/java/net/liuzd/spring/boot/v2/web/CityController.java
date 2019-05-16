package net.liuzd.spring.boot.v2.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.liuzd.spring.boot.v2.domain.City;
import net.liuzd.spring.boot.v2.mapper.CityMapper;

@Controller
public class CityController {

	@Autowired
	private CityMapper cityMapper;

	@GetMapping("/city")
	@ResponseBody
	@Transactional(readOnly = true)
	public City helloWorld() {
		return  cityMapper.selectCityById(1L);
	}

}