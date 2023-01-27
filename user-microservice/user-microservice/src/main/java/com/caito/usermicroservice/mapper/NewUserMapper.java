package com.caito.usermicroservice.mapper;

import com.caito.usermicroservice.dto.NewUserDTO;
import com.caito.usermicroservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewUserMapper {

    User newUserDTOToUser(NewUserDTO request);
}
