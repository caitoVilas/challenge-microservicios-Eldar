package com.caito.carmicroservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(NotFondException.class)
    protected ResponseEntity<ErrroDTO> notFoundException(Exception e, HttpServletRequest request) {
        ErrroDTO response = new ErrroDTO(404, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
