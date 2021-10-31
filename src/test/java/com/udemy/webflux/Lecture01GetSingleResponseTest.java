package com.udemy.webflux;

import com.udemy.webflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture01GetSingleResponseTest extends BaseTest{

  @Autowired
  private WebClient webClient;

  @Test
  public void blockTest() {

    ResponseDTO response = this.webClient
        .get()
        .uri("reactive-math/square/{number}", 5)
        .retrieve()
        .bodyToMono(ResponseDTO.class) // Mono<Resposne>
        .block();

    System.out.println(response);
  }

  @Test
  public void stepVerifierTest() {

    Mono<ResponseDTO> responseMono = this.webClient
        .get()
        .uri("reactive-math/square/{number}", 5)
        .retrieve()
        .bodyToMono(ResponseDTO.class); // Mono<Resposne>

    StepVerifier.create(responseMono)
        .expectNextMatches( response -> response.getOutput() == 25)
        .verifyComplete();
  }

}
