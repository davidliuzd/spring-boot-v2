package net.liuzd.spring.boot.v2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = ElasticsearchProperties.ELASTIC_SEARCH_PREFIX)
@Configuration
public class ElasticsearchProperties {

    public static final String ELASTIC_SEARCH_PREFIX = "spring.data.elasticsearch";

    private String             userIndex;

    private String             userType;

    private String             bookIndex;

    private String             bookType;

}
