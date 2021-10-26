package com.udemy.webflux.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author fferlin
 */

@Data
@ToString
public class MultiplyRequestDTO {

    private int first;
    private int second;
}
