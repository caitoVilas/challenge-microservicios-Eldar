package com.caito.usermicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private Long userId;
}
