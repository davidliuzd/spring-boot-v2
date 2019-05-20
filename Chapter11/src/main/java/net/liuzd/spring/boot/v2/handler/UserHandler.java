package net.liuzd.spring.boot.v2.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import net.liuzd.spring.boot.v2.domain.User;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

    private Map<Long, User>                       userMap = new HashMap<>();

    private java.util.concurrent.atomic.LongAdder la      = new LongAdder();

    public UserHandler() {
        la.increment();
        User user = new User(la.longValue(), "刘志远", "123", 20);
        userMap.put(user.getId(), user);
        la.increment();
        user = new User(la.longValue(), "刘志立", "456", 24);
        userMap.put(user.getId(), user);
    }

    // http://localhost:8888/saveUser
    //TODO 未生效，界面一直在加载中
    public Mono<ServerResponse> saveUser(ServerRequest request) {
        Mono<User> mongoUser = request.bodyToMono(User.class);
        User user = mongoUser.block();
        la.increment();
        userMap.put(la.longValue(), user);
        //
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(user));
    }

    // http://localhost:8888/deleteUser/1
    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("id"));
        userMap.remove(userId);
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("success"));
    }

    // http://localhost:8888/user/1
    public Mono<ServerResponse> getUser(ServerRequest request) {
        Long userId = Long.valueOf(request.pathVariable("id"));
        User user = userMap.get(userId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(user));
    }

    // http://localhost:8888/listUser
    public Mono<ServerResponse> listUser(ServerRequest request) {
        List<User> userList = new ArrayList<>(userMap.values());
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(userList));
    }
}
