package net.liuzd.spring.boot.v2.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import net.liuzd.spring.boot.v2.domain.Book;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book,Long> {
    
}