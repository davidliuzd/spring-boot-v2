package net.liuzd.spring.boot.v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import net.liuzd.spring.boot.v2.dao.BlogRepository;
import net.liuzd.spring.boot.v2.domain.Blog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository        blogRepository;

    @Autowired
    private ElasticsearchTemplate template;

    // 删除所有blog并插入两条记录
    @Before
    public void doBefore() {
        blogRepository.deleteAll();

        Blog blog1 = new Blog();
        blog1.setId("1");
        blog1.setTitle("Java开发指南");
        blog1.setContent("Java可运行于多个平台，如Windows, Mac OS，及其他多种UNIX版本的系统。");
        Calendar c1 = Calendar.getInstance();
        c1.set(2009, Calendar.JUNE, 12);
        blog1.setCreateTime(c1.getTime());
        blog1.setReadSize(10);
        blog1.setTags(Arrays.asList("Java", "跨平台"));
        blog1.setUsername("ace");
        blogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setId("2");
        blog2.setTitle("Elasticsearch简介");
        blog2.setContent("Elasticsearch是一个实时分布式搜索和分析引擎。它让你以前所未有的速度处理大数据成为可能。");
        Calendar c2 = Calendar.getInstance();
        c2.set(2016, Calendar.JANUARY, 15);
        blog2.setCreateTime(c2.getTime());
        blog2.setReadSize(20);
        blog2.setTags(Arrays.asList("Elasticsearch", "搜索", "大数据"));
        blog2.setUsername("ace1");
        blogRepository.save(blog2);
    }

    // 测试查找所有并排序；根据ID查找；根据用户名模糊查找；
    @Test
    public void basicTest() {
        Iterable<Blog> blogs = blogRepository.findAll(new Sort(Sort.Direction.DESC, "createTime"));
        for (Blog blog : blogs) {
            System.out.println(blog);
        }

        Optional<Blog> optionalBlog = blogRepository.findById("1");
        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            System.out.println(blog);
        }

        Page<Blog> page = blogRepository.findByUsernameContaining("ace", PageRequest.of(0, 5));
        for (Blog blog : page.getContent()) {
            System.out.println(blog);
        }
    }

    /**
     * 需求:根据多个关键字搜索blog,搜索范围包括title、content，结果根据阅读量倒序排序
     */
    @Test
    public void searchTest1() {
        QueryBuilder builder = QueryBuilders.multiMatchQuery("Java 大数据", "title", "content");
        Pageable pageable = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "readSize"));
        Page<Blog> page = blogRepository.search(builder, pageable);
        for (Blog blog : page.getContent()) {
            System.out.println(blog);
        }
    }

    /**
     * 需求：根据多个标签精确过滤blog,结果根据阅读量倒序排序
     */
    @Test
    public void searchTest2() {

        QueryBuilder builder = QueryBuilders.termsQuery("tags", "Java", "搜索");
        Pageable pageable = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "readSize"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withFilter(builder).withPageable(pageable).build();
        Page<Blog> page = blogRepository.search(searchQuery);
        for (Blog blog : page.getContent()) {
            System.out.println(blog);
        }
    }

    /**
     * 需求：根据多个关键词检索title和content字段，按阅读量逆序，结果高亮关键词，
     */
    @Test
    public void searchTest3() {
        QueryBuilder builder = QueryBuilders.multiMatchQuery("Elasticsearch Java", "title", "content");
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
        Pageable pageable = PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "readSize"));
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withHighlightFields(highlightTitle,
                highlightContent).withPageable(pageable).build();

        AggregatedPage<Blog> page = template.queryForPage(searchQuery, Blog.class, new SearchResultMapper() {

            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Blog> blogList = new ArrayList<>();
                if (response.getHits().getHits().length <= 0) {
                    return null;
                }
                for (SearchHit hit : response.getHits().getHits()) {
                    Blog blog = new Blog();
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();

                    try {
                        BeanUtils.populate(blog, sourceAsMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    blog.setTitle(highlightFields.get("title").getFragments()[0].string());
                    blog.setContent(highlightFields.get("content").getFragments()[0].string());

                    blogList.add(blog);
                }

                return new AggregatedPageImpl(blogList, pageable, blogList.size());
            }
        });

        for (Blog blog : page.getContent()) {
            System.out.println(blog);
        }

    }

}
