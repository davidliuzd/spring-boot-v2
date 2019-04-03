package net.liuzd.spring.boot.v2.common;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class BaseEntity {

    private Long    id;

    private Integer mark;

    private Date    createTime;

    private Date    modifyTime;

    private Integer version;

}
