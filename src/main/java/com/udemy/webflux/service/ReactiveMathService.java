package com.udemy.webflux.service;

import com.udemy.webflux.dto.MultplyRequestDTO;
import com.udemy.webflux.dto.ResponseDTO;
import java.time.Duration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {

  public Mono<ResponseDTO> findSquare(int input) {
    return Mono.fromSupplier(() -> input * input)
        .map(ResponseDTO::new);
  }

  public Flux<ResponseDTO> multiplicationTable(int input) {
    return Flux.range(1, 10)
        .delayElements(Duration.ofSeconds(1)) // no-blocking sleep
        //.doOnNext(i -> SleepUtil.sleepSeconds(1))
        .doOnNext(i -> System.out.println("math-service processing: " + i))
        .map(i -> new ResponseDTO(i * input));
  }

  public Mono<ResponseDTO> multiply(Mono<MultplyRequestDTO> dtoMono) {
    return dtoMono
            .map(dto -> dto.getFirst() * dto.getSecond())
            .map(ResponseDTO::new);
  }

}
