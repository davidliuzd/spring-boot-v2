package net.liuzd.spring.boot.v2.web.controller;

import java.util.Date;
import java.util.Random;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.dao.UserRepository;
import net.liuzd.spring.boot.v2.domain.User;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private User get(long i) {
        return new User(i, "liuzidongxx" + i, new Random().nextInt(150), "davidliuzd@sina.com", new Date(), new Date(),
                "ignore-Jackson" + i, 1,null);
    }

    @GetMapping("save")
    public String save() {
        User user = get(new Random().nextLong());
        log.debug("user : " + user);
        userRepository.save(user);
        return "success";
    }

    @GetMapping("delete")
    public String delete(long id) {
        log.debug("delete : " + id);
        userRepository.deleteById(id);
        return "success";
    }

    @PostMapping("update")
    public String update(@RequestBody User user) {
        userRepository.save(user);
        return "success";
    }

    @GetMapping("getOne")
    public User getOne(long id) {
        return userRepository.findById(id).get();
    }

    // 每页数量
    private Integer PAGESIZE = 10;

    @GetMapping("getUsersList")
    public Page<User> getList(Integer pageNumber, String query) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        // es搜索默认第一页页码是0
        SearchQuery searchQuery = getEntitySearchQuery(pageNumber, PAGESIZE, query);
        return userRepository.search(searchQuery);
    }

    private SearchQuery getEntitySearchQuery(int pageNumber, int pageSize, String searchContent) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("mark", 1));
        //
        BoolQueryBuilder filterQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.hasLength(searchContent)) {
            filterQueryBuilder.must(QueryBuilders.matchPhraseQuery("name", searchContent).slop(1));
        }
        // 设置分页
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new NativeSearchQueryBuilder().withPageable(pageable).withQuery(boolQueryBuilder).withFilter(
                filterQueryBuilder).build();
    }
}