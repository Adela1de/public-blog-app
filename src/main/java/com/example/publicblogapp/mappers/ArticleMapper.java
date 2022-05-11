package com.example.publicblogapp.mappers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.model.entities.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ArticleMapper {

    public static final ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    public abstract ArticleDTO toArticleDTO(Article article);
}
