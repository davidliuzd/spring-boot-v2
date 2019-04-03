package net.liuzd.spring.boot.v2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.liuzd.spring.boot.v2.mapper")
public class Application {

	public static void main(String[] args) {	   
		SpringApplication.run(Application.class, args);
	}

}
