package com.udemy.webflux;

import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.exception.InputValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture05BadRequestTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  @Test
  public void badRequestTest() {

    Mono<ResponseDTO> responseMono = this.webClient
        .get()
        .uri("reactive-math/square/{number}/throw", 5)
        .retrieve()
        .bodyToMono(ResponseDTO.class)
        .doOnNext(System.out::println)
        .doOnError(err -> System.out.println(err.getMessage()));

    StepVerifier.create(responseMono)
        .verifyError(WebClientResponseException.BadRequest.class);
  }

}
