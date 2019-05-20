package net.liuzd.spring.boot.v2.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import net.liuzd.spring.boot.v2.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final Map<Long, User> data = new ConcurrentHashMap<>();

    public UserService() {
        User user = new User(1L, "刘志希", "123", 20);
        data.put(user.getId(), user);
    }

    public Flux<User> list() {
        return Flux.fromIterable(this.data.values());
    }

    public Flux<User> getById(final Flux<Long> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.data.get(id)));
    }

    public Mono<User> getById(final Long id) {
        return Mono.justOrEmpty(this.data.get(id)).switchIfEmpty(Mono.error(new Exception()));
    }

    public Mono<User> createOrUpdate(final User user) {
        this.data.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> delete(final Long id) {
        return Mono.justOrEmpty(this.data.remove(id));
    }
}