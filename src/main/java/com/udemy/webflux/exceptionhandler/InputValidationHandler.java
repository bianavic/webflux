package com.udemy.webflux.exceptionhandler;

import com.udemy.webflux.dto.InputFailedValidationResponse;
import com.udemy.webflux.exception.InputValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author fferlin
 */

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputFailedValidationResponse> handleException(InputValidationException e) {

        InputFailedValidationResponse response = new InputFailedValidationResponse();
        response.setErrorCode(e.getErrorCode());
        response.setInput(e.getInput());
        response.setMessage(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
