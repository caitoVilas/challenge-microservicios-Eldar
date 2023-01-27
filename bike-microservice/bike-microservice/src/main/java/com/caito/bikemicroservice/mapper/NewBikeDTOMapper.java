package com.caito.bikemicroservice.mapper;

import com.caito.bikemicroservice.dto.NewBikeDTO;
import com.caito.bikemicroservice.entity.Bike;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewBikeDTOMapper {

    Bike newBikeToBike(NewBikeDTO request);
}
