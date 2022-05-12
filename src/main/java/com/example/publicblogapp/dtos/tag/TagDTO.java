package com.example.publicblogapp.dtos.tag;

import com.example.publicblogapp.model.entities.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TagDTO {

    private Long id;
    private String text;
    @JsonIgnore
    private List<Article> articles;

}
