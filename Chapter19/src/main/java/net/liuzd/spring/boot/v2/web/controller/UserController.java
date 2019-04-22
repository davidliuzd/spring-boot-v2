package net.liuzd.spring.boot.v2.web.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.domain.FastjsonUser;
import net.liuzd.spring.boot.v2.domain.JacksonUser;

@Slf4j
@RestController
public class UserController {

    @GetMapping(value = { "/fastjson" })
    public ResponseEntity<FastjsonUser> AlibabaJson() {
        log.debug("getUser ...");
        return ResponseEntity.ok(new FastjsonUser(1L, "liuzidongxx", 25, "davidliuzd@sina.com", new Date(),
                LocalDateTime.now(), "ignore-fastjson"));
    }

    @GetMapping(value = { "/Jackson" })
    public ResponseEntity<JacksonUser> Jackson() {
        log.debug("Jackson ...");
        return ResponseEntity.ok(new JacksonUser(1L, "liuzidongxx", 25, "davidliuzd@sina.com", new Date(), LocalDateTime
                .now(), "ignore-Jackson"));
    }
}