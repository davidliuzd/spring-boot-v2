package net.liuzd.spring.boot.v2.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import net.liuzd.spring.boot.v2.handler.HiHandler;
import net.liuzd.spring.boot.v2.handler.UserHandler;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
    
    @Bean
    public RouterFunction<ServerResponse> routeHi(HiHandler hiHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/hi")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        hiHandler::Hi);
    }
    
    @Bean
    public RouterFunction<ServerResponse> routeUser(UserHandler userHandler) {        
        return RouterFunctions
                .route(RequestPredicates.GET("/listUser")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::listUser)
                .andRoute(RequestPredicates.GET("/findUser/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::getUser)
                .andRoute(RequestPredicates.GET("/deleteUser/{id}")
                                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                        userHandler::deleteUser)
                
                .andRoute(RequestPredicates.POST("/saveUser")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::saveUser);
    }

}
