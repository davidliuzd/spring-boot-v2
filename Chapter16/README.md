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


#### ServletContext静态工具类
```
 /**
     * 通过静态方法获得当前的request对象
     * @return 当前线程对应的request对象
     */
    public static HttpServletRequest request() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 通过静态方法获得当前的response对象
     * @return 当前线程对应的response对象
     */
    public static HttpServletResponse response() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 通过静态方法获得当前的session对象
     * @return 当前线程对应的session对象
     */
    public static HttpSession session() {
        return request().getSession();
    }
```


#### SpringContext静态工具类
```
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static <T> T getBean(@Nonnull Class<T> clazz) {
        return ctx.getBean(clazz);
    }

    public static boolean contain(String name) {
        return ctx.containsBean(name);
    }

    public static boolean isSingleton(String name) {
        return ctx.isSingleton(name);
    }

}
```

#### UserControllerTest
```
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Log4j2
public class UserControllerTest {

    private MockMvc               mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/list").accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testFindId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 1L).accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    private User get(int i) {
        User bean = new User();
        bean.setName("天涯 " + i);
        bean.setAge(18 + i);
        bean.setEmail(i + "davidliuzd@sina.com");
        bean.setMark(1);
        bean.setVersion(1);
        bean.setStatus(UserStatusEnum.NORMAL);
        return bean;
    }

    @Test
    public void testCreate() throws Exception {
        User po = get(new Random().nextInt());
        String json = JSONUtils.toJson(po);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user").contentType(
                MediaType.APPLICATION_JSON_UTF8_VALUE).content(json).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testUpdate() throws Exception {
        User bean = get(new Random().nextInt());
        bean.setId(1L);
        String json = JSONUtils.toJson(bean);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/id").contentType(
                MediaType.APPLICATION_JSON_UTF8_VALUE).content(json).accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(MockMvcResultHandlers.print()).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

    @Test
    public void testDel() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", 1L).accept(
                MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(MockMvcResultHandlers.print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        log.error("result : " + content);
        Assert.assertEquals(200, status);
    }

}
```