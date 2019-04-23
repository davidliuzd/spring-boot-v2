package net.liuzd.spring.boot.v2.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{userIndex}", type = "#{userType}")
public class User {

    private Long       id;

    private String     name;

    private Integer    age;

    private String     email;

    /**
     * 格式化日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date       createTime;

    /**
     * 格式化日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date       modifyTime;

    /**
     * 转换为json时不包括该属性
     */
    @JsonIgnore
    private String     ignore;

    private Integer    mark;

    @Field(type = FieldType.Nested)
    private List<Book> book;

}
