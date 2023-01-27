package com.caito.carmicroservice.mapper;

import com.caito.carmicroservice.dto.CarDTO;
import com.caito.carmicroservice.dto.NewCarDTO;
import com.caito.carmicroservice.entity.Car;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO carToCarDTO(Car request);
    List<CarDTO> carListToCarDTOList(List<Car> request);
    Car carDTOtoCar(NewCarDTO request);
}
