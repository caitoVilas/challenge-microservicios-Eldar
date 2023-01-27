package com.caito.bikemicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewBikeDTO {

    private String brand;
    private String model;
    private Long userId;
}
