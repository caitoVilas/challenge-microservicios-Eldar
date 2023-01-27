package com.caito.carmicroservice.service.contract;

import com.caito.carmicroservice.dto.CarDTO;
import com.caito.carmicroservice.dto.NewCarDTO;
import com.caito.carmicroservice.entity.Car;

import java.util.List;

public interface CarService {

    List<CarDTO> getAll();
    CarDTO getById(Long id);
    CarDTO createCar(NewCarDTO car);
    List<CarDTO> byUserId(Long id);
}
