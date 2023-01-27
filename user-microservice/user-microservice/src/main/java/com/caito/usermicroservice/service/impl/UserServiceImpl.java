package com.caito.usermicroservice.service.impl;

import com.caito.usermicroservice.dto.NewBikeDTO;
import com.caito.usermicroservice.dto.NewCarDTO;
import com.caito.usermicroservice.dto.NewUserDTO;
import com.caito.usermicroservice.dto.UserDTO;
import com.caito.usermicroservice.entity.User;
import com.caito.usermicroservice.exceptions.NotFoundException;
import com.caito.usermicroservice.mapper.NewUserMapper;
import com.caito.usermicroservice.mapper.UserMapper;
import com.caito.usermicroservice.model.BikeDTO;
import com.caito.usermicroservice.model.CarDTO;
import com.caito.usermicroservice.repository.UserRepository;
import com.caito.usermicroservice.service.contract.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private NewUserMapper newUserMapper;
    @Autowired
    private UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public List<UserDTO> getAll() {
        logger.info("inicio servicio lisrado de usuarios");
        return userMapper.userListToUserDTOList(userRepository.findAll());
    }

    @Override
    public UserDTO getById(Long id) {
        logger.info("inicio servicio busqueda de usuario");
        User user = userRepository.findById(id).orElse(null);
        return userMapper.userToUserDTO(user);
    }

    @Override
    public UserDTO save(NewUserDTO user) {
        logger.info("inicio servicio creacion de usuario");
        return userMapper.userToUserDTO(userRepository.save(newUserMapper.newUserDTOToUser(user))) ;
    }

    @Override
    public List<CarDTO> getCars(Long id) {
        logger.info("inicio servicio de busqueda de autos por usuario");
        List<CarDTO> cars = restTemplate.getForObject("http://localhost:8002/api/v1/cars/user/" + id,
                List.class);
        return cars;
    }

    @Override
    public List<BikeDTO> getBikes(Long id) {
        logger.info("inicio sevicio de motos por usuario");
        List<BikeDTO> bikes = restTemplate.getForObject("http://localhost:8003/api/v1/bikes/user/" + id,
                List.class);
        return bikes;
    }

    @Override
    public CarDTO createCar(NewCarDTO car) {
        logger.info("llamado a  sercio de creacion de autos http://localhost:8002/api/v1/cars/");
        CarDTO carNew;
        try {
            carNew = restTemplate.postForObject("http://localhost:8002/api/v1/cars/", car, CarDTO.class);
        }catch (HttpClientErrorException e){
            logger.error("el usuario no existe");
            throw new NotFoundException("el usuario no existe");
        }
        return carNew;
    }

    @Override
    public BikeDTO createBike(NewBikeDTO bike) {
        logger.info("llamado a servicio creacion de moto http://localhost:8003/api/v1/bikes/");
        BikeDTO bikeNew;
        try {
            bikeNew = restTemplate.postForObject("http://localhost:8003/api/v1/bikes/", bike, BikeDTO.class);
        }catch (HttpClientErrorException e){
            logger.error("el usuario no existe");
            throw new NotFoundException("el usuario no existe");
        }

        return bikeNew;
    }

    @Override
    public Map<String, Object> getUserAndVehicles(Long userId) {
        logger.info("inicio servicio busqueda de vehiculos por usuario");
        Map<String, Object> result = new HashMap<>();
        logger.info("buscar usuario");
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            logger.error("el usuario no existe");
            result.put("Mensaje", "No existe el usuario");
            return result;
        }
        result.put("Usuario", user);
        logger.info("obteniendo autos... ");
        List<CarDTO> cars = getCars(userId);
        logger.info("obteniendo motos...");
        List<BikeDTO> bikes = getBikes(userId);
        if (cars == null){
            result.put("Autos", "El usuario no tiene autos");
        }else {
            result.put("Autos", cars);
        }
        if (bikes == null){
            result.put("Motos", "El usuario no tiene motos");
        }else {
            result.put("Motos", bikes);
        }
        return result;
    }
}
