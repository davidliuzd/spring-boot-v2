package net.liuzd.spring.boot.v2.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.domain.User;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/map")
    public ResponseEntity<Map<?, ?>> map() {
        log.debug("map ...");   
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "liuzidong");
        map.put("age", 25);
        map.put("email", "davidliuzd@sina.com");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/get")
    public ResponseEntity<User> user() {
        log.debug("user ...");
        return ResponseEntity.ok(new User(1L, "liuzidong", 25, "davidliuzd@sina.com"));
    }

}