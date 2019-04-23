package net.liuzd.spring.boot.v2.domain;

import java.math.BigDecimal;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "#{bookIndex}", type = "#{bookType}")
public class Book {

    private Long       id;

    /** 书名 */
    private String     name;

    /** 作者 */
    private String     author;

    /** 价格 */
    private BigDecimal money;

}
