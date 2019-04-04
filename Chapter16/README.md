## [mybatis enum转换参考](https://github.com/Shiyajian/pretty-boot-demo)
### 划重点

#### spring boot application.yml配置
```
mybatis:
  configuration:
    default-enum-type-handler: net.liuzd.spring.boot.v2.config.enums.EnumTypeHandler
```

#### java enum转换处理
```
public class EnumTypeHandler<E extends Enumerable> extends BaseTypeHandler<E> {

    private Class<E> enumType;

    public EnumTypeHandler() { /* instance */ }


    public EnumTypeHandler(@Nonnull Class<E> enumType) {
        this.enumType = enumType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, e.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int value = rs.getInt(columnName);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return rs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value = cs.getInt(columnIndex);
        return cs.wasNull() ? null : EnumUtil.of(this.enumType, value);
    }

}
```


