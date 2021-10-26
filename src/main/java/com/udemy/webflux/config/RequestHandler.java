package com.udemy.webflux.config;

import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author fferlin
 */

@Service
public class RequestHandler {

    @Autowired
    private ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int input = Integer.valueOf(serverRequest.pathVariable("input")); // accessing the variable
        Mono<ResponseDTO> responseDTOMono = this.reactiveMathService.findSquare(input); // building the pipeline
        return ServerResponse.ok().body(responseDTOMono, ResponseDTO.class);
    }

    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        int input = Integer.valueOf(serverRequest.pathVariable("input")); // accessing the variable
        Flux<ResponseDTO> responseFlux = this.reactiveMathService.multiplicationTable(input); // building the pipeline
        return ServerResponse.ok().body(responseFlux, ResponseDTO.class);
    }

}
