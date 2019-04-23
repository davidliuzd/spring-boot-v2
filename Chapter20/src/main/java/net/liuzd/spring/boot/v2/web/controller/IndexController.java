package net.liuzd.spring.boot.v2.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        log.debug("index...");
        model.addAttribute("host", "https://my.oschina.net/liuzidong");
        return "index";
    }
   
}