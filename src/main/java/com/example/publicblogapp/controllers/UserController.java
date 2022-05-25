package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.event.RegistrationCompleteEvent;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.mappers.impl.ConvertArticlesForFrontEnd;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.requests.article.ArticleFindAllRequest;
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
    private final ConvertArticlesForFrontEnd convertArticlesForFrontEnd;
    private final ApplicationEventPublisher publisher;
    
    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterRequestBody userRegisterRequestBody,
                                             final HttpServletRequest request)
    {
        var user = UserMapper.INSTANCE.toUser(userRegisterRequestBody);
        var registeredUser = userService.registerUser(user);
        publisher.publishEvent(new RegistrationCompleteEvent(registeredUser));
        String url =
                applicationUrl(request) +
                "/users/verifyRegistration?token=" +
                userService.
                getVerificationTokenForUser(registeredUser.getId());
        return ResponseEntity.ok().body(url);
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
    public ResponseEntity<Iterable<ArticleFindAllRequest>> findFavorites(@PathVariable Long userId)
    {
        var favorites = userService.findFavorites(userId);
        var favoritesConverted =
                favorites.
                stream().
                map(convertArticlesForFrontEnd::convertToArticleFindAllRequest).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(favoritesConverted);
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
