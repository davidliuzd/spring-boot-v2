package net.liuzd.spring.boot.v2.generator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GeneratorData {

    /**
     * 作者
     */
    private String author;

    /**
     * 数据库连接url
     */
    private String url;

    /**
     * 数据库连接用户名
     */
    private String userName;

    /**
     * 数据库连接用户密码
     */
    private String password;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 包根目录
     */
    private String packageParentName;

    /**
     * 表名
     */
    private String tableName;

    private String superEntityClass;

    private String superControllerClass;

}
