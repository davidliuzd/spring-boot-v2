package net.liuzd.spring.boot.v2.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Map<String, Object> model) {
        model.put("host", "https://my.oschina.net/liuzidong");
        return "index";
    }

}