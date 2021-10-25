package com.udemy.webflux.controller;

import com.udemy.webflux.dto.MultplyRequestDTO;
import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
public class ReactiveMathController {

  @Autowired
  private ReactiveMathService reactiveMathService;

  @GetMapping("square/{input}")
  public Mono<ResponseDTO> findSquare(@PathVariable int input) {
    return this.reactiveMathService.findSquare(input);
  }

  @GetMapping("table/{input}")
  public Flux<ResponseDTO> multiplicationTable(@PathVariable int input) {
    return this.reactiveMathService.multiplicationTable(input);
  }

  @GetMapping(value = "table/{input}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ResponseDTO> multiplicationTableStream(@PathVariable int input) {
    return this.reactiveMathService.multiplicationTable(input);
  }

  @PostMapping("multiply")
  public Mono<ResponseDTO> multply(@RequestBody Mono<MultplyRequestDTO> requestDTOMono) {
    return this.reactiveMathService.multiply(requestDTOMono);

  }

}
