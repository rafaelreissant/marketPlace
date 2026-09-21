package com.example.marketPlace.controller;

import com.example.marketPlace.controller.DTO.UserDTO;
import com.example.marketPlace.controller.mapper.UserMapper;
import com.example.marketPlace.model.User;
import com.example.marketPlace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDTO userDTO){
        User user = userMapper.toUser(userDTO);
        userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") String id){
        var idUser = UUID.fromString(id);
        User user = userService.getUserById(idUser);
        return ResponseEntity.ok(userMapper.toUserDTO(user));

    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTOList = userService.getAllUser()
                .stream().map(userMapper::toUserDTO).toList();

        if (userDTOList.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userDTOList);
    }
}