package com.udemy.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

public class Lecture01GetSingleResponseTest extends BaseTest{

  @Autowired
  private WebClient webClient;

}