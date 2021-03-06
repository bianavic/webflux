package com.udemy.webflux.config;

import com.udemy.webflux.dto.InputFailedValidationResponse;
import com.udemy.webflux.exception.InputValidationException;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author fferlin
 */

@Configuration
public class RouterConfig {

    @Autowired
    private RequestHandler requestHandler;

    @Bean // main bean
    public RouterFunction<ServerResponse> highLevelRouter() {

        return RouterFunctions.route()
            .path("router", this::serverResponseRouterFunction)
            .build();
    }

    //@Bean
    private RouterFunction<ServerResponse> serverResponseRouterFunction() {

        return RouterFunctions.route()
            .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),requestHandler::squareHandler)
            .GET("square/{input}", req -> ServerResponse.badRequest().bodyValue("only 10-19 allowed"))
            .GET("table/{input}", requestHandler::tableHandler)
            .GET("table/{input}/stream", requestHandler::tableStreamHandler)
            .POST("multiply", requestHandler::multiplyHandler)
            .GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
            .onError(InputValidationException.class, exceptionhandler())
            .build();
    }

    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionhandler() {
        return (err, req) -> {
            InputValidationException ex = (InputValidationException) err;
            InputFailedValidationResponse response = new InputFailedValidationResponse();
            response.setInput(ex.getInput());
            response.setMessage(ex.getMessage());
            response.setErrorCode(ex.getErrorCode());
            return ServerResponse.badRequest().bodyValue(response);
        };
    }

}
