package com.example.publicblogapp.controllers;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private ArticleService articleService;

    @GetMapping
    public ResponseEntity<Iterable<Article>> findAll()
    {
        var articles = articleService.findAll();
        return ResponseEntity.ok().body(articles);
    }
}
