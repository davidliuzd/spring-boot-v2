###  Data JPA示例

#### 1. [SpringBootApplication注解分析](https://www.jianshu.com/p/4e1cab2d8431)
```
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM,
				classes = AutoConfigurationExcludeFilter.class) })
```


#### 2. CustomerController构造函数自动对象，经分析由[ComponentScan](https://blog.51cto.com/4247649/2118342)注解完成的：
```
public CustomerController(CustomerRepository customerRepository,CustomerService customerService) {
	    //ComponentScan自动注入对象
		this.customerRepository = customerRepository;		
}
作用如下
2.1 自定扫描路径下边带有@Controller，@Service，@Repository，@Component注解加入spring容器
2.2 通过includeFilters加入扫描路径下没有以上注解的类加入spring容器
2.3 通过excludeFilters过滤出不用加入spring容器的类
2.4 自定义增加了@Component注解的注解方式
```

