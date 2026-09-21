package com.example.marketPlace.controller.mapper;

import com.example.marketPlace.controller.DTO.UserDTO;
import com.example.marketPlace.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);
}
