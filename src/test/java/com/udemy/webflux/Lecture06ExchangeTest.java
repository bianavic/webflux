package com.udemy.webflux;

import com.udemy.webflux.dto.InputFailedValidationResponse;
import com.udemy.webflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture06ExchangeTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  // exchange = retrieve + addtional information (ex: http status code)
  @Test
  public void badRequestTest() {

    Mono<Object> responseMono = this.webClient
        .get()
        .uri("reactive-math/square/{number}/throw", 5)
        .exchangeToMono(this::exchange)
        .doOnNext(System.out::println)
        .doOnError(err -> System.out.println(err.getMessage()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  private Mono<Object> exchange(ClientResponse clientResponse) {
    if (clientResponse.rawStatusCode() == 400)
      return clientResponse.bodyToMono(InputFailedValidationResponse.class);
    else
      return clientResponse.bodyToMono(ResponseDTO.class);
  }

}
