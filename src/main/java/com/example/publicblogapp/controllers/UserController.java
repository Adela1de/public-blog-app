package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.event.RegistrationCompleteEvent;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.requests.user.UserRegisterRequestBody;
import com.example.publicblogapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    private final ApplicationEventPublisher publisher;

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
    
    @PostMapping(path = "/register")
    public ResponseEntity<Void> registerUser(@RequestBody UserRegisterRequestBody userRegisterRequestBody,
                                             final HttpServletRequest request)
    {
        var user = UserMapper.INSTANCE.toUser(userRegisterRequestBody);
        var registeredUser = userService.registerUser(user);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return ResponseEntity.noContent().build();
    }

    private String applicationUrl(HttpServletRequest request)
    {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

    @GetMapping("/verifyRegistration")
    public ResponseEntity<String> verifyRegistration(@RequestParam("token") String token)
    {
        var result = userService.validateVerificationToken(token);

        if(result.equalsIgnoreCase("valid")) return ResponseEntity.ok().body("User created successfully");
        return ResponseEntity.badRequest().body("Bad User");
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
