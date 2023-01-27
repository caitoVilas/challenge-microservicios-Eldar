package com.caito.bikemicroservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDTO {

    private int code;
    private LocalDateTime timestamp;
    private String message;
    private String path;
}
