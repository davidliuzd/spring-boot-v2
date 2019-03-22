package net.liuzd.spring.boot.v2.web;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.liuzd.spring.boot.v2.entity.User;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @RequestMapping("/save")
    public void save(@RequestBody @Valid User user, BindingResult result) {
        log.debug("save user ...");
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get(@RequestParam(name = "grade", required = true) int grade,
            @RequestParam(name = "classroom", required = true) int classroom) {       
        System.out.println(grade + "," + classroom);
    }

}