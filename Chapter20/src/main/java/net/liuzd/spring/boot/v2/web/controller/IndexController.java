package net.liuzd.spring.boot.v2.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.config.MyData;

@Slf4j
@Controller
public class IndexController {

    @Resource
    private MyData data;

    @RequestMapping("/")
    public String index(Model model) {
        log.debug("index...");
        model.addAttribute("host", "https://my.oschina.net/liuzidong");
        return "index";
    }

    @GetMapping("/myData")
    @ResponseBody
    public ResponseEntity<MyData> myData() throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        log.debug("myData...");       
        return ResponseEntity.ok(data);
    }

}