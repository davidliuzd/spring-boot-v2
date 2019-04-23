package net.liuzd.spring.boot.v2;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.assertj.core.util.Lists;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.dao.BookRepository;
import net.liuzd.spring.boot.v2.dao.UserRepository;
import net.liuzd.spring.boot.v2.domain.Book;
import net.liuzd.spring.boot.v2.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository        userRepository;

    @Autowired
    private BookRepository        bookRepository;

    @Autowired
    private ElasticsearchTemplate template;

    @Before
    public void doBefore() {
        //userRepository.deleteAll();
        //bookRepository.deleteAll();
        log.debug("del all");
    }

    private User get(long i, Book book) {
        return new User(i, "davidliuzd" + i, new Random().nextInt(200), "davidliuzd@sina.com" + i, new Date(),
                new Date(), "ignore" + i, 1, Lists.newArrayList(book));
    }

    private Book get(long i) {
        return new Book(i, "name" + i, "author" + i, new BigDecimal(i));
    }

    @Test
    public void saves() {

        int size = 1000;
        for (int i = 1; i <= size; i++) {
            Book book = get(i);
            User user = get(i, book);
            userRepository.save(user);
            bookRepository.save(book);
            log.debug("添加第：" + i + "个");
        }
        log.debug("添加完成了！");
    }

    @Test
    public void page() {

        String search = "";
        Pageable pageable = PageRequest.of(1, 10);// 构建分页参数

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("mark", 1));

        if (StringUtils.isNotEmpty(search)) {
            filterQueryBuilder.should(QueryBuilders.matchPhraseQuery("name", search).slop(1));
        }

        NestedQueryBuilder nestedQueryBuilder = null;
        if (StringUtils.isNotEmpty(search)) {
            BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.wildcardQuery("name", "*"
                    + search + "*"));
            nestedQueryBuilder = QueryBuilders.nestedQuery("book", queryBuilder, ScoreMode.None);
        }
        if (null != nestedQueryBuilder) {
            filterQueryBuilder.should(nestedQueryBuilder);
        }
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).withFilter(
                filterQueryBuilder).withSort(sort).withPageable(pageable).build();
        Page<User> userPage = userRepository.search(searchQuery);
        log.debug("userPage : " + userPage.getTotalElements());
        log.debug("userPage : " + userPage.getContent());
    }

    @Test
    public void update() {
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("mark", 0);
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(String.valueOf(1)).withClass(User.class)
                .withIndexRequest(indexRequest).build();
        template.update(updateQuery);
    }

}
