package com.udemy.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author fferlin
 */

@Configuration
public class RouterConfig {

    @Autowired
    private RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> serverResponseRouterFunction() {

        return RouterFunctions.route()
                .GET("/router/square/{input}", requestHandler::squareHandler)
                .GET("/router/table/{input}", requestHandler::tableHandler)
                .build();

    }
}
