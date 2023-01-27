package com.caito.bikemicroservice.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bikes")
@Data
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Long userId;
}
