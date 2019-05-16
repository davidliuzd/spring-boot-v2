#### MyBatis XML

#### JPA
```
数据表的初始化可以通过在Spring Boot的application.properties中进行配置spring.jpa.hibernate.ddl-auto=update来实现

spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://172.16.4.203:3306/mydatabase?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.platform=org.hibernate.dialect.MySQL57Dialect
```

#### MyBatis
```
因为SpringBoot在启动时，只有检测到spring.datasource.initialization-mode=ALWAYS配置，后再检测spring.datasource.schema之后，且配置的sql角本命令不为空，才会去执行schema和spring.datasource.data

spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql
spring.datasource.sql-script-encoding=utf-8
spring.datasource.initialization-mode=ALWAYS
```
