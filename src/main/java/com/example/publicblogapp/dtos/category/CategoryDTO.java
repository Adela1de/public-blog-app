package com.example.publicblogapp.dtos.category;

import com.example.publicblogapp.model.entities.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {

    private Long id;
    private String name;
    @JsonIgnore
    private List<Article> articles;
}
