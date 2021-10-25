package com.udemy.webflux.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author fferlin
 */

@Data
@ToString
public class InputFailedValidationResponse {

    private int errorCode;
    private int input;
    private String message;

}
