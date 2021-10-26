package com.udemy.webflux.config;

import com.udemy.webflux.dto.MultiplyRequestDTO;
import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.exception.InputValidationException;
import com.udemy.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        int input = Integer.valueOf(serverRequest.pathVariable("input")); // accessing the variable
        Flux<ResponseDTO> responseFlux = this.reactiveMathService.multiplicationTable(input); // building the pipeline
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseFlux, ResponseDTO.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        Mono<MultiplyRequestDTO> requestDTOMono = serverRequest.bodyToMono(MultiplyRequestDTO.class);
        Mono<ResponseDTO> responseDTOMono = this.reactiveMathService.multiply(requestDTOMono);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(responseDTOMono, ResponseDTO.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
        int input = Integer.valueOf(serverRequest.pathVariable("input"));
        if (input < 10 || input > 20) {
            return Mono.error(new InputValidationException(input)); // error signal
        }
        Mono<ResponseDTO> responseDTOMono = this.reactiveMathService.findSquare(input);
        return ServerResponse.ok().body(responseDTOMono, ResponseDTO.class);
    }

}
