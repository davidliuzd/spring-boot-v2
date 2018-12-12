package net.liuzd.spring.boot.v2.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//Document指定数据插入到MongoDB数据库里的名字为textCollection的集合中
@Document(collection = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -1L;
	//id属性是给mongodb用的，用@Id注解修饰
    @Id
    private Long id;
    private String userName;
    private Integer age;


}
