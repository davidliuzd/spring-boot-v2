package net.liuzd.spring.boot.v2.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ElasticsearchProperties.class)
public class ElasticsearchConfiguration {

    @Bean(value = "userIndex")
    public String userIndex(ElasticsearchProperties pro) {
        return pro.getUserIndex();
    }

    @Bean(value = "userType")
    public String userType(ElasticsearchProperties pro) {
        return pro.getUserType();
    }

    @Bean(value = "bookIndex")
    public String bookIndex(ElasticsearchProperties pro) {
        return pro.getBookIndex();
    }

    @Bean(value = "bookType")
    public String bookType(ElasticsearchProperties pro) {
        return pro.getBookType();
    }

}