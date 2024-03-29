package com.example.publicblogapp.dtos.user;

import com.example.publicblogapp.model.entities.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String userName;
    private String email;
    private List<Article> articles;
    private List<Article> favorites;

}
