package net.liuzd.spring.boot.v2.domain;

import java.time.LocalDateTime;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class FastjsonUser {

    private Long          id;

    private String        name;

    private Integer       age;

    private String        email;

    /**
     * 格式化日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date          ct;

    /**
     * 格式化日期
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;

    /**
     * 转换为json时不包括该属性
     */
    @JSONField(serialize = false)
    private String        ignore;

}
