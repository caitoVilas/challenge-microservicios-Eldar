package com.caito.carmicroservice.conroller;

import com.caito.carmicroservice.dto.CarDTO;
import com.caito.carmicroservice.dto.NewCarDTO;
import com.caito.carmicroservice.entity.Car;
import com.caito.carmicroservice.service.contract.CarService;
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
@RequestMapping("/api/v1/cars")
@Tag(name = "Cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    @Operation(description = "listado de autos", summary = "listado de autos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<CarDTO>> getAll(){

        List<CarDTO> cars = carService.getAll();
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    @Operation(description = "obtener in auto por id si esiste", summary = "obtener un auto por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CarDTO> getById(@PathVariable Long id){

        CarDTO car = carService.getById(id);
        if (car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    @PostMapping
    @Operation(description = "crear un auto", summary = "crear un auto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CarDTO> createCar(@RequestBody NewCarDTO car){

        return new ResponseEntity<>(carService.createCar(car), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @Operation(description = "obtener autos de un usaurio por id", summary = "obtener autos de un usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<CarDTO>> getByUserId(@PathVariable Long userId){

        List<CarDTO> cars = carService.byUserId(userId);
        if (cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }
}
