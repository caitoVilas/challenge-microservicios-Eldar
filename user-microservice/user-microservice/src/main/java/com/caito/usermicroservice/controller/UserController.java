package com.caito.usermicroservice.controller;

import com.caito.usermicroservice.dto.NewBikeDTO;
import com.caito.usermicroservice.dto.NewCarDTO;
import com.caito.usermicroservice.dto.NewUserDTO;
import com.caito.usermicroservice.dto.UserDTO;
import com.caito.usermicroservice.model.BikeDTO;
import com.caito.usermicroservice.model.CarDTO;
import com.caito.usermicroservice.service.contract.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(description = "Listado de usuarios", summary = "Listado de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<UserDTO>> getAll(){

        List<UserDTO> users = userService.getAll();
        if (users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(description = "obtener un usuario por id si existe", summary = "obtener un usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UserDTO> getById(@PathVariable Long id){

        UserDTO user = userService.getById(id);
        if (user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(description = "crear un usuario", summary = "crear un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UserDTO> createUser(@RequestBody NewUserDTO
                                                          user){

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/cars/{userId}")
    @Operation(description = "lista de autos de un usuario", summary = "lista de autos de un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<CarDTO>> getCars(@PathVariable Long userId){

        List<CarDTO> cars = userService.getCars(userId);
        if (cars == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    @Operation(description = "lista de motos de un usuario", summary = "lista de motos de un usuario")
    public ResponseEntity<List<BikeDTO>> getBikes(@PathVariable Long userId){

        List<BikeDTO> bikes = userService.getBikes(userId);
        if (bikes == null)
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/create-car")
    @Operation(description = "creacion de un auto para un usuario",
            summary = "creacion de un auto para un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CarDTO> createCar(@RequestBody NewCarDTO car){

        return ResponseEntity.ok(userService.createCar(car));
    }

    @PostMapping("/create-bike")
    @Operation(description = "creacion de una moto para un usuario",
            summary = "creacion de una moto para un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<BikeDTO> createBike(@RequestBody NewBikeDTO bike){

        return ResponseEntity.ok(userService.createBike(bike));
    }

    @GetMapping("/user-and-vehicles/{userId}")
    @Operation(description = "listado de vhiculos de un usuario",
    summary = "listado de vhiculos de un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<Map<String, Object>> getUserAndVehicles(@PathVariable Long userId){

        return ResponseEntity.ok(userService.getUserAndVehicles(userId));
    }
}
