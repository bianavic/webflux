package com.udemy.webflux.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ResponseDTO {

  private Date date = new Date();
  private int output;

  public ResponseDTO(int output) {
    this.output = output;
  }
}
