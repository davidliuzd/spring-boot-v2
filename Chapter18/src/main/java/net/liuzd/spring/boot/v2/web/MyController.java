package net.liuzd.spring.boot.v2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @GetMapping("/my")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("my");
        view.addObject("host", "https://my.oschina.net/liuzidong");
        return view;
    }

}