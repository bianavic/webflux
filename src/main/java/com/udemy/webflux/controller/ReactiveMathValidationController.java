package com.udemy.webflux.controller;

import com.udemy.webflux.dto.InputFailedValidationResponse;
import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.exception.InputValidationException;
import com.udemy.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author fferlin
 */

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathValidationController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping("square/{input}/throw")
    public Mono<ResponseDTO> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20)
            throw new InputValidationException(input);
        return this.reactiveMathService.findSquare(input);
    }

    @GetMapping("square/{input}/mono-error")
    public Mono<ResponseDTO> monoError(@PathVariable int input) {
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (integer >= 10 && integer <= 20)
                        sink.next(integer);
                    else
                        sink.error(new InputValidationException(integer));
                })
                .cast(Integer.class)
                .flatMap(i -> this.reactiveMathService.findSquare(i));
    }

}
