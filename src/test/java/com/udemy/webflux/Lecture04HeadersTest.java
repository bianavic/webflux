package com.udemy.webflux;

import com.udemy.webflux.dto.MultiplyRequestDTO;
import com.udemy.webflux.dto.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture04HeadersTest extends BaseTest{

  @Autowired
  private WebClient webClient;

  @Test
  public void headersTest() {

    Mono<ResponseDTO> responseMono = this.webClient
        .post()
        .uri("reactive-math/multiply")
        .bodyValue(buildRequestDto(5, 2))
        .headers(h -> h.set("someKey", "someValue"))
        .retrieve()
        .bodyToMono(ResponseDTO.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  private MultiplyRequestDTO buildRequestDto(int a, int b) {
    MultiplyRequestDTO dto = new MultiplyRequestDTO();
    dto.setFirst(a);
    dto.setSecond(b);
    return dto;
  }

}
