package com.caito.carmicroservice.service.impl;

import com.caito.carmicroservice.dto.CarDTO;
import com.caito.carmicroservice.dto.NewCarDTO;
import com.caito.carmicroservice.entity.Car;
import com.caito.carmicroservice.exception.NotFondException;
import com.caito.carmicroservice.mapper.CarMapper;
import com.caito.carmicroservice.mapper.NewCarMapper;
import com.caito.carmicroservice.models.User;
import com.caito.carmicroservice.repository.CarRepository;
import com.caito.carmicroservice.service.contract.CarService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CarMapper carMapper;
    @Autowired
    private NewCarMapper newCarMapper;

    private static  final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public List<CarDTO> getAll() {
        logger.info("servicio listado de autos");
        return carMapper.carListToCarDTOList(carRepository.findAll());
    }

    @Override
    public CarDTO getById(Long id) {
        logger.info("Inicio servicio buscar auto");
        Car car = carRepository.findById(id).orElse(null);
        if (car == null){
            logger.error("auto no enconteado");
            return null;
        }
        return carMapper.carToCarDTO(car);
    }

    @Override
    public CarDTO createCar(NewCarDTO car) {
        logger.info("inicio servicio guardado autos");
        logger.info("llamado a servicio de usarios " + "http://localhost:8001/users/" + car.getUserId());
        try {
            User user = restTemplate.getForObject("http://localhost:8001/users/" + car.getUserId(),
                    User.class);
        }catch (HttpClientErrorException e){
            logger.error("el usuario no existe");
            throw new NotFondException("el usuario no existe");
        }
        logger.info("guardando auto");
        return carMapper.carToCarDTO(carRepository.save(carMapper.carDTOtoCar(car)));
    }

    @Override
    public List<CarDTO> byUserId(Long id) {
        logger.info("servicio busqueda de auto por id de usuario");
        return carMapper.carListToCarDTOList(carRepository.findByuserId(id));
    }
}
