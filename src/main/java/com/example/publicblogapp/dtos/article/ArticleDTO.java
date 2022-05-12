package com.example.publicblogapp.dtos.article;

import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.model.entities.Tag;
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
}
