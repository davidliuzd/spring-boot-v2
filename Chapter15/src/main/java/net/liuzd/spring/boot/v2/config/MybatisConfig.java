package net.liuzd.spring.boot.v2.config;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author miemie
 * @since 2018-08-12
 */
@Configuration
@MapperScan("net.liuzd.spring.boot.v2.mapper")
public class MybatisConfig {

}
