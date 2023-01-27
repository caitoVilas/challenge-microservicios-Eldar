package com.caito.usermicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestHandlerException {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorDTO> notFoundException(Exception e, HttpServletRequest request){
        ErrorDTO response = new ErrorDTO(404, LocalDateTime.now(), e.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
