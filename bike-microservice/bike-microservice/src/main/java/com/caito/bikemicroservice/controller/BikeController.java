package com.caito.bikemicroservice.controller;

import com.caito.bikemicroservice.dto.BikeDTO;
import com.caito.bikemicroservice.dto.NewBikeDTO;
import com.caito.bikemicroservice.entity.Bike;
import com.caito.bikemicroservice.service.contract.BikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bikes")
@Tag(name = "Bike")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    @Operation(description = "listado de motos", summary = "listado de motos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<BikeDTO>> getAll(){

        List<BikeDTO> bikes = bikeService.getAll();
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    @Operation(description = "devuelve una moto por id si existe", summary = "devuelve una moto por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<BikeDTO> getbyId(@PathVariable Long id){

        BikeDTO bike = bikeService.getById(id);
        if (bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }

    @PostMapping
    @Operation(description = "creacion de una moto", summary = "creacion de una moto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<BikeDTO> createBike(@RequestBody NewBikeDTO bike){

        return new ResponseEntity<>(bikeService.createBike(bike), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "lista de motoe por usuario", summary = "lista de motos por usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<BikeDTO>> getByUser(@PathVariable Long userId){

        List<BikeDTO> bikes = bikeService.getByUserId(userId);
        if (bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }
}
