package net.liuzd.spring.boot.v2.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import net.liuzd.spring.boot.v2.domain.Book;
import net.liuzd.spring.boot.v2.domain.User;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "my-data", ignoreInvalidFields = true)
//@PropertySource(value = { "config/random.properties" })
public class MyData {

    private String       secret;
    
    private int          intNumber;
    
    private int          lessTen;
    
    private int          range;
    
    private long         longNumber;
    
    private String       uuid;
    
    private String       projectName;

    private String       version;

    private List<String> springBootFuns;

    private List<Book>   books;
    
    private Map<String,String> userNameMap;
    
    private Map<String,User> userMap;
    
    private Map<String,List<Book>> userBookMap;

}
