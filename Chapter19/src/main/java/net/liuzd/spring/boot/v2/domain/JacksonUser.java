package net.liuzd.spring.boot.v2.domain;

import java.time.LocalDateTime;
import java.util.Date;

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
public class JacksonUser {

    private Long          id;

    private String        name;

    private Integer       age;

    private String        email;

    /**
     * 格式化日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
   // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date          ct;

    /**
     * 格式化日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;

    /**
     * 转换为json时不包括该属性
     */
    @JsonIgnore
    private String        ignore;

}
