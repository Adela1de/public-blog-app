package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.requests.article.ArticlePostRequestBody;
import com.example.publicblogapp.requests.article.ArticlePutRequestBody;
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

    @GetMapping(path = "user/{userId}/{articleId}")
    public ResponseEntity<ArticleDTO> findByUserAndId(@PathVariable Long userId, @PathVariable Long articleId)
    {
        var article = articleService.findByUserAndId(userId, articleId);
        var articleDTO = ArticleMapper.INSTANCE.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Void> createArticle
            (@PathVariable Long userId, @RequestBody ArticlePostRequestBody articlePostRequestBody)
    {
        var article = ArticleMapper.INSTANCE.toArticle(articlePostRequestBody);
        var createdArticle = articleService.createArticle(article, userId);
        var uri =
                ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(createdArticle.getId()).
                toUri();

        return ResponseEntity.created(uri).body(null);
    }

    @PutMapping(path = "/{articleId}")
    public ResponseEntity<ArticleDTO> updateArticle
            (@PathVariable Long articleId, @RequestBody ArticlePutRequestBody articlePutRequestBody)
    {
        var article = ArticleMapper.INSTANCE.toArticle(articlePutRequestBody);
        var updatedArticle = articleService.updateArticle(articleId, article);
        var updatedArticleDTO = ArticleMapper.INSTANCE.toArticleDTO(updatedArticle);
        return ResponseEntity.ok().body(updatedArticleDTO);
    }

    @DeleteMapping(path = "/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId)
    {
        articleService.deleteArticle(articleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/filters/{filterId}")
    public ResponseEntity<Iterable<ArticleDTO>> findByFilter(@PathVariable Long filterId)
    {
        var filteredArticles = articleService.findByFilter(filterId);
        var filteredArticlesDTO =
                filteredArticles.
                stream().
                map(ArticleMapper.INSTANCE::toArticleDTO).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(filteredArticlesDTO);
    }

    @PutMapping(path = "/favorites/{userId}/{articleId}")
    public ResponseEntity<UserDTO> addFavorites
            (@PathVariable Long userId, @PathVariable Long articleId)
    {
        var updatedUser = articleService.addFavorites(userId, articleId);
        var updatedUserDTO = UserMapper.INSTANCE.toUserDTO(updatedUser);
        return ResponseEntity.ok().body(updatedUserDTO);
    }

    @GetMapping(path = "/find")
    public ResponseEntity<ArticleDTO> findByTitle(@RequestParam String title)
    {
        var article = articleService.findByTitle(title);
        var articleDTO = ArticleMapper.INSTANCE.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }
}
