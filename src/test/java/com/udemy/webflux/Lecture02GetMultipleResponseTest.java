package com.udemy.webflux;

import com.udemy.webflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture02GetMultipleResponseTest extends BaseTest{

  @Autowired
  private WebClient webClient;

  @Test
  public void stepVerifierTest() {

    Flux<ResponseDTO> responseFlux = this.webClient
        .get()
        .uri("reactive-math/table/{number}", 5)
        .retrieve()
        .bodyToFlux(ResponseDTO.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseFlux)
        .expectNextCount(10)
        .verifyComplete();
  }

}
