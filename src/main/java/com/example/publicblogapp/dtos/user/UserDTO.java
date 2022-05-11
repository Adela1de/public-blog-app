package com.example.publicblogapp.dtos.user;

import com.example.publicblogapp.model.entities.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String userName;
    private String email;
    private String passWord;
    private List<Article> articles;

}
