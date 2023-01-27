package com.caito.bikemicroservice.service.contract;

import com.caito.bikemicroservice.dto.BikeDTO;
import com.caito.bikemicroservice.dto.NewBikeDTO;
import com.caito.bikemicroservice.entity.Bike;

import java.util.List;

public interface BikeService {

    List<BikeDTO> getAll();
    BikeDTO getById(Long id);
    BikeDTO createBike(NewBikeDTO bike);
    List<BikeDTO> getByUserId(Long id);
}
