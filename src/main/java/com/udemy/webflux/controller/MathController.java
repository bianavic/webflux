package com.udemy.webflux.controller;

import com.udemy.webflux.dto.ResponseDTO;
import com.udemy.webflux.service.MathService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("math")
public class MathController {

  @Autowired
  private MathService mathService;

  @GetMapping("square/{input}")
  public ResponseDTO findSquare(@PathVariable int input) {
    return this.mathService.findSquare(input);
  }

  @GetMapping("table/{input}")
  public List<ResponseDTO> multiplicationTable(@PathVariable int input) {
    return this.mathService.multiplicationTable(input);
  }

}
