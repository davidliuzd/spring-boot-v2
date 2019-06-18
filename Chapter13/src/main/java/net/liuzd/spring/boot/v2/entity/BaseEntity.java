package net.liuzd.spring.boot.v2.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 基础父类测试
 * </p>
 */
@Data
@Accessors(chain = true)
public class BaseEntity {

    //数据库指定为自增
    @TableId(type = IdType.AUTO) 
    private Long    id;

    @TableLogic
    private Integer mark;

    @TableField(fill = FieldFill.INSERT)
    private Date    createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date    modifyTime;
    
    @Version
    private Integer version;

}
