package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.requests.article.ArticlePostRequestBody;
import com.example.publicblogapp.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping(path = "/{articleId}")
    public ResponseEntity<ArticleDTO> findById(@PathVariable Long articleId)
    {
        var article = articleService.findById(articleId);
        var articleDTO = ArticleMapper.INSTANCE.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Iterable<ArticleDTO>> findByUser(@PathVariable Long userId)
    {
        var articles = articleService.findByUser(userId);
        var articlesDTO =
                articles.
                stream().
                map(ArticleMapper.INSTANCE::toArticleDTO).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(articlesDTO);
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Void> createArticle
            (@PathVariable Long userId, @RequestBody ArticlePostRequestBody articlePostRequestBody)
    {
        var article = ArticleMapper.INSTANCE.toArticleDTO(articlePostRequestBody);
        var createdArticle = articleService.createArticle(article, userId);
        var uri =
                ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(createdArticle.getId()).
                toUri();

        return ResponseEntity.created(uri).body(null);
    }
}
