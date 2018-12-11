package net.liuzd.spring.boot.v2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import net.liuzd.spring.boot.v2.domain.User;

public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
