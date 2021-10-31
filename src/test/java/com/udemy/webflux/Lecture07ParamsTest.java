package com.udemy.webflux;

import java.net.URI;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture07ParamsTest extends BaseTest {

  @Autowired
  private WebClient webClient;

  String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";

  @Test
  public void queryParamsTest() {

    URI uri = UriComponentsBuilder.fromUriString(queryString)
        .build(10, 20);

    Flux<Integer>  integerFlux = this.webClient
        .get()
        .uri(uri)
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(System.out::println);

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

  // without an uri builder initialization
  @Test
  public void queryParamsWithoutURIBuilderTest() {

    Flux<Integer>  integerFlux = this.webClient
        .get()
        .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(10, 20))
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(System.out::println);

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

  // with map
  @Test
  public void queryParamsWithMapTest() {

    Map<String, Integer> map = Map.of(
        "count", 10,
        "page", 20
    );

    Flux<Integer>  integerFlux = this.webClient
        .get()
        .uri(b -> b.path("jobs/search").query("count={count}&page={page}").build(map))
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(System.out::println);

    StepVerifier.create(integerFlux)
        .expectNextCount(2)
        .verifyComplete();
  }

}
