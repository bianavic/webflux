package com.udemy.webflux.config;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class CalculatorHandler {

  // multiple handlers intentionally
  // calculator/{a}/{b}
  public Mono<ServerResponse> additionHandler(ServerRequest request) {
    int a = getValue(request, "a");
    int b = getValue(request, "b");
    return ServerResponse.ok().bodyValue(a + b);
  }

  public Mono<ServerResponse> subtractionHandler(ServerRequest request) {
    int a = getValue(request, "a");
    int b = getValue(request, "b");
    return ServerResponse.ok().bodyValue(a - b);
  }

  public Mono<ServerResponse> multiplicationHandler(ServerRequest request) {
    int a = getValue(request, "a");
    int b = getValue(request, "b");
    return ServerResponse.ok().bodyValue(a * b);
  }

  public Mono<ServerResponse> divisionHandler(ServerRequest request) {
    int a = getValue(request, "a");
    int b = getValue(request, "b");
    return ServerResponse.ok().bodyValue(a / b);
  }

  private int getValue(ServerRequest request, String key) {
    return Integer.parseInt(request.pathVariable(key));
  }

}
