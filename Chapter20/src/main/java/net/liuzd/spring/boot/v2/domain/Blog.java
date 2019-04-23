package net.liuzd.spring.boot.v2.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@Document(indexName = "blog", type = "blog", shards = 3, replicas = 0) //本地测试，因此复制分片数设为0
public class Blog {

    @Id//主键
    private String id;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")//指定为Text类型，参与全文检索。设置分词器
    private String title;

    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Keyword)
    private String username; //blog 作者

    @Field(type = FieldType.Date, index = false)//index设为false不做全文检索，默认为true
    private Date createTime; //创建时间

    @Field(type = FieldType.Integer, index = false)
    private Integer readSize = 0; // 阅读量

    @Field(type = FieldType.Keyword)
    private List<String> tags; //标签   对应es中存储结构 ["Spring Data", "Spring Boot"]
}