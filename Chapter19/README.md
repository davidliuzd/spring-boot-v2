#### Fastjson + Jackson + ConditionalOnExpression

##### 1. ConditionalOnExpression（读取配置文件参数来决定是否启用）
```
resources:
    #应用名称
    appname: spring-boot-example
    #日志打印的基础扫描包  
    basepackage: net.liuzd.spring.boot.v2  
    web:
       # false为启用Jackson
       fastjson: true

@Bean
    /**
     * 通过resources.web.fastjson来决定是否使用：fastjson，反之Jackson
     * */
    @ConditionalOnExpression("#{${resources.web.fastjson}==true}")
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 4、将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
```

##### 2. com.alibaba.fastjson.annotation.JSONField
```
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date          ct;

	/**
     * 转换为json时不包括该属性
     */
    @JSONField(serialize = false)
    private String        ignore;
```

###### 3. com.fasterxml.jackson.annotation.JsonFormat
```
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dt;

    /**
     * 转换为json时不包括该属性
     */
    @JsonIgnore
    private String        ignore;
```

##### 4. UserController
```
@Slf4j
@RestController
public class UserController {

    @GetMapping(value = { "/fastjson" })
    public ResponseEntity<FastjsonUser> AlibabaJson() {
        log.debug("getUser ...");
        return ResponseEntity.ok(new FastjsonUser(1L, "liuzidongxx", 25, "davidliuzd@sina.com", new Date(),
                LocalDateTime.now(), "ignore-fastjson"));
    }

    @GetMapping(value = { "/Jackson" })
    public ResponseEntity<JacksonUser> Jackson() {
        log.debug("Jackson ...");
        return ResponseEntity.ok(new JacksonUser(1L, "liuzidongxx", 25, "davidliuzd@sina.com", new Date(), LocalDateTime
                .now(), "ignore-Jackson"));
    }
}
```