package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> findAll()
    {
        var users = userService.findAll();
        var usersDTO = users.stream().map(UserMapper.INSTANCE::toUserDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }
}
