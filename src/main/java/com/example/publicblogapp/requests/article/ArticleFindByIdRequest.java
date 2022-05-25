package com.example.publicblogapp.requests.article;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleFindByIdRequest {

    private Long id;
    private String title;
    private String username;
    private List<String> comments;
    private List<String> commentedBy;
}
