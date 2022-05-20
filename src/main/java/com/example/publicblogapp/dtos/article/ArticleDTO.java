package com.example.publicblogapp.dtos.article;

import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.model.entities.Comment;
import com.example.publicblogapp.model.entities.Filter;
import com.example.publicblogapp.model.entities.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDTO {

    private Long id;
    private String title;
    private String text;
    private List<Category> categories;
    private List<Tag> tags;
    private List<Filter> filters;
    @JsonIgnore
    private List<Comment> comments;

}
