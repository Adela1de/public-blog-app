package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.event.RegistrationCompleteEvent;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.requests.user.UserLogInRequestBody;
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

    @GetMapping("/resendVerificationToken")
    public ResponseEntity<String> resendVerificationToken(@RequestParam("token") String oldToken,
                                                          HttpServletRequest request)
    {
        var newToken = userService.generateNewVerificationToken(oldToken);
        return ResponseEntity.ok().body(resendVerificationTokenMail(request, newToken));
    }

    private String resendVerificationTokenMail(HttpServletRequest request, String token)
    {
        var url = applicationUrl(request) + "/users/verifyRegistration?token="+token;
        return "Token resend successfully, lick the link to verify account: " + url;
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

    @PostMapping(path = "/login")
    public ResponseEntity<UserDTO> userLogIn(@RequestBody UserLogInRequestBody userLogInRequestBody)
    {
        User user;
        var email = userLogInRequestBody.getEmail();
        var password = userLogInRequestBody.getPassword();

        user = userService.userLogIn(email, password);

        var userLoggedDTO = UserMapper.INSTANCE.toUserDTO(user);
        return ResponseEntity.ok().body(userLoggedDTO);
    }

}
