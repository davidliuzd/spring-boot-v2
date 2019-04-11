package net.liuzd.spring.boot.v2.web.controller;

import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.domain.User;

@Slf4j
@RestController
@RequestMapping("/xss")
public class XssController {

    @GetMapping("/head")
    public ResponseEntity<String> head(
            @RequestHeader(name = "tag", required = false, defaultValue = "test") String tag) {
        log.debug("tag : {}", tag);
        return ResponseEntity.ok(tag);
    }

    @GetMapping("/param")
    public ResponseEntity<String> param(
            @RequestParam(name = "name", required = false, defaultValue = "davidliuzd") String name) {
        log.debug("param : {}", name);
        return ResponseEntity.ok(name);
    }

    @GetMapping("/params")
    public ResponseEntity<String[]> param(
            @RequestParam(name = "names", required = false, defaultValue = "davidliuzds") String[] names) {
        log.debug("params : {}", Arrays.toString(names));
        return ResponseEntity.ok(names);
    }

    @PostMapping("/user")
    public ResponseEntity<User> user(@RequestBody User user) {
        log.debug("user ...");
        return ResponseEntity.ok(user);
    }

}