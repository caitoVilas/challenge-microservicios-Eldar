package com.caito.bikemicroservice.mapper;

import com.caito.bikemicroservice.dto.BikeDTO;
import com.caito.bikemicroservice.entity.Bike;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BikeMapper {

    BikeDTO bikeToBikeDTO(Bike request);
    List<BikeDTO> bikeListToBikeDTOList(List<Bike> request);
    Bike bikeDTOToBike(BikeDTO request);
}
