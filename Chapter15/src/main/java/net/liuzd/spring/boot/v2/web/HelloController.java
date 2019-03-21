package net.liuzd.spring.boot.v2.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HelloController {

    @RequestMapping({"/","/hello"})
    public String index() {
        log.debug("hello word ...");
        return "Hello World";
    }

}