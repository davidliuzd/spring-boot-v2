package net.liuzd.spring.boot.v2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HelloController {

    @GetMapping(value= {"","hello"})
    public String Hello() {
        return "Hello this is SpringWebFlux";
    }
    
    @GetMapping("hellos")
    public Mono<String> sayHelloWorld() {
        return Mono.just("Hello World");
    }

}
