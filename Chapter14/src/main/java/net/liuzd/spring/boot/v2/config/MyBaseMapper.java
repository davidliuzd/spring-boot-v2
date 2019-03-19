package net.liuzd.spring.boot.v2.config;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author miemie
 * @since 2018-08-13
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 自定义通用方法
     */
    Integer deleteAll();
    
    //https://blog.csdn.net/moonpure/article/details/80759880    
    /** 
    * @Title: insertBatch 
    * @Description: 
    *  SqlServer 对语句的条数和参数的数量都有限制，分别是 1000 和 2100。                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
        Mysql 对语句的长度有限制，默认是 4M：可能根据版本不同吧
        （特别注意：mysql默认接受sql的大小是1048576(1M)，即第三种方式若数据量超过1M会报如下异常：
        （可通过调整MySQL安装目录下的my.ini文件中[mysqld]段的＂max_allowed_packet = 1M＂））
        Mybatis 对动态语句没有数量上的限制 
        -----------------------------------------------------------------
        官方文档：通用 insertBatch 为什么放在 service 层处理
        SQL 长度有限制海量数据量单条 SQL 无法执行，就算可执行也容易引起内存泄露 JDBC 连接超时等
        不同数据库对于单条 SQL 批量语法不一样不利于通用
        目前的解决方案：循环预处理批量提交，虽然性能比单 SQL 慢但是可以解决以上问题。
    * @param list
    * @return  Integer   
    */
    Integer insertBatch(@Param("List") List<T> list);
}
