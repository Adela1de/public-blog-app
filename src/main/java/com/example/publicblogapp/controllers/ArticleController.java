package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/articles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<Iterable<ArticleDTO>> findAll()
    {
        var articles = articleService.findAll();
        var articlesDTO =
                articles.
                stream().
                map(ArticleMapper.INSTANCE::toArticleDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(articlesDTO);
    }
}
