package net.liuzd.spring.boot.v2.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础父类测试
 * </p>
 * @author hubin
 * @since 2018-08-11
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    private Integer mark;

    private Date    createTime;

    private Date    modifyTime;
    
    private Integer version;    
    
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

}
