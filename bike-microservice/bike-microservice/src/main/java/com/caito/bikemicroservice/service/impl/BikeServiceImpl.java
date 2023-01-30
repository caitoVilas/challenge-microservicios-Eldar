package com.caito.bikemicroservice.service.impl;

import com.caito.bikemicroservice.dto.BikeDTO;
import com.caito.bikemicroservice.dto.NewBikeDTO;
import com.caito.bikemicroservice.entity.Bike;
import com.caito.bikemicroservice.exception.NotFondException;
import com.caito.bikemicroservice.mapper.BikeMapper;
import com.caito.bikemicroservice.mapper.NewBikeDTOMapper;
import com.caito.bikemicroservice.model.User;
import com.caito.bikemicroservice.repository.BikeRepository;
import com.caito.bikemicroservice.service.contract.BikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private BikeMapper bikeMapper;
    @Autowired
    private NewBikeDTOMapper newBikeDTOMapper;

    private static final Logger logger = LoggerFactory.getLogger(BikeServiceImpl.class);

    @Value("${base.user.url}")
    private String baseUserUrl;


    @Override
    public List<BikeDTO> getAll() {
        logger.info("inicio servicio listado de motos");
        return bikeMapper.bikeListToBikeDTOList(bikeRepository.findAll());
    }

    @Override
    public BikeDTO getById(Long id) {
        logger.info("inicio servicio busqueda moto");
        Bike bike = bikeRepository.findById(id).orElse(null);
        if (bike ==null){
            logger.error("moto no encontrada");
            return null;
        }
        return bikeMapper.bikeToBikeDTO(bike);
    }

    @Override
    public BikeDTO createBike(NewBikeDTO bike) {
        logger.info("inicio servicio creacion motos");
        try {
            logger.info("llamando a servicio de usuarios " + baseUserUrl+ bike.getUserId());
            User user = restTemplate.getForObject(baseUserUrl+ bike.getUserId(),
                    User.class);
        }catch (HttpClientErrorException e){
            logger.error("el usuario no existe");
            throw new NotFondException("el usuario no existe");
        }
        logger.info("guardando moto");
        return bikeMapper.bikeToBikeDTO(bikeRepository.save(newBikeDTOMapper.newBikeToBike(bike)));
    }

    @Override
    public List<BikeDTO> getByUserId(Long id) {
        logger.info("inicio servicio busqueda de moto por id de usuario");
        return bikeMapper.bikeListToBikeDTOList(bikeRepository.findByUserId(id));
    }
}
