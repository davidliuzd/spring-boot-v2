package net.liuzd.spring.boot.v2.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import net.liuzd.spring.boot.v2.domain.Blog;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {

    //方法名定义查询:根据用户名模糊查询
    Page<Blog> findByUsernameContaining(String username, Pageable pageable);
}