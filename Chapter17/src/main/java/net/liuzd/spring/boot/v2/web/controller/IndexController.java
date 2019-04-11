package net.liuzd.spring.boot.v2.web.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.domain.User;

@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        log.debug("index...");
        model.addAttribute("host", "https://my.oschina.net/liuzidong");
        model.addAttribute("user", get());
        return "index";
    }

    private User get() {
        return new User(1L, "liuzidongxx", 25, "davidliuzd@sina.com");
    }

    @GetMapping(value = { "/getUser" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> getUser() {
        log.debug("getUser ...");
        return ResponseEntity.ok(get());
    }
}