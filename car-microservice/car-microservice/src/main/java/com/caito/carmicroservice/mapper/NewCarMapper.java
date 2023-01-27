package com.caito.carmicroservice.mapper;

import com.caito.carmicroservice.dto.NewCarDTO;
import com.caito.carmicroservice.entity.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewCarMapper {

    Car newCarDTOToCar(NewCarDTO request);
}
