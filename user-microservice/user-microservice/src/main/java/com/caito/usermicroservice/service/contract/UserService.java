package com.caito.usermicroservice.service.contract;

import com.caito.usermicroservice.dto.NewBikeDTO;
import com.caito.usermicroservice.dto.NewCarDTO;
import com.caito.usermicroservice.dto.NewUserDTO;
import com.caito.usermicroservice.dto.UserDTO;
import com.caito.usermicroservice.model.BikeDTO;
import com.caito.usermicroservice.model.CarDTO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO save(NewUserDTO user);
    List<CarDTO> getCars(Long id);
    List<BikeDTO> getBikes(Long id);
    CarDTO createCar(NewCarDTO car);
    BikeDTO createBike(NewBikeDTO bike);
    Map<String, Object> getUserAndVehicles(Long userId);
}
