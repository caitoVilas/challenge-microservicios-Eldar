package com.caito.usermicroservice.mapper;

import com.caito.usermicroservice.dto.UserDTO;
import com.caito.usermicroservice.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User request);
    List<UserDTO> userListToUserDTOList(List<User> request);
}
