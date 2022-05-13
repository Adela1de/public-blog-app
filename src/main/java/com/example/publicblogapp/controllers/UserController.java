package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.requests.user.UserPostRequestBody;
import com.example.publicblogapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long userId)
    {
        var user = userService.findById(userId);
        var userDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok().body(userDTO);
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserPostRequestBody userPostRequestBody)
    {
        var user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        var createdUser = userService.create(user);
        var uri =
                ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(null);
    }

    @GetMapping(path = "/favorites/{userId}")
    public ResponseEntity<Iterable<ArticleDTO>> findFavorites(@PathVariable Long userId)
    {
        var favorites = userService.findFavorites(userId);
        var favoritesDTO =
                favorites.
                stream().
                map(ArticleMapper.INSTANCE::toArticleDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(favoritesDTO);
    }

}
