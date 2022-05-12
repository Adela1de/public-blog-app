package com.example.publicblogapp.dtos.filter;

import com.example.publicblogapp.model.entities.Article;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDTO {

    private Long id;
    private String text;
    @JsonIgnore
    private List<Article> articles;
}
