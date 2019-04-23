package net.liuzd.spring.boot.v2.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import net.liuzd.spring.boot.v2.domain.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User,Long> {
    
}