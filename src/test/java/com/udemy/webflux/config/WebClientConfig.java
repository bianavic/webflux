package com.udemy.webflux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class WebClientConfig {

  // test auth at runtime
  @Bean
  public WebClient webClient() {
    return WebClient.builder()
        .baseUrl("http://localhost:8080")
        .filter(this::sessionToken)
        .build();
  }

  private Mono<ClientResponse> sessionToken(ClientRequest request, ExchangeFunction ex) {
    System.out.println("generating session token");
    // build a new cliente request from the existed request
    ClientRequest clientRequest = ClientRequest.from(request)
        .headers(h -> h.setBearerAuth("some-length-jwt"))
        .build();

    return ex.exchange(clientRequest);
  }

}
