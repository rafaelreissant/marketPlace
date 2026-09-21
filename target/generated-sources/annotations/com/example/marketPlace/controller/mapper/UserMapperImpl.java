package com.example.marketPlace.controller.mapper;

import com.example.marketPlace.controller.DTO.UserDTO;
import com.example.marketPlace.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-09-17T18:15:17-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDTO.name() );
        user.setPassword( userDTO.password() );
        user.setEmail( userDTO.email() );

        return user;
    }

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String password = null;
        String email = null;

        name = user.getName();
        password = user.getPassword();
        email = user.getEmail();

        UserDTO userDTO = new UserDTO( name, password, email );

        return userDTO;
    }
}
