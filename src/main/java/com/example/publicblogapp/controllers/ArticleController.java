package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.article.ArticleDTO;
import com.example.publicblogapp.dtos.user.UserDTO;
import com.example.publicblogapp.mappers.ArticleMapper;
import com.example.publicblogapp.mappers.UserMapper;
import com.example.publicblogapp.mappers.impl.ConvertArticlesForFrontEnd;
import com.example.publicblogapp.requests.article.*;
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
    private final ConvertArticlesForFrontEnd convertArticlesForFrontEnd;

    @GetMapping
    public ResponseEntity<Iterable<ArticleFindAllRequest>> findAll()
    {
        var articles = articleService.findAll();
        var articlesConverted =
                articles.
                stream().
                map(convertArticlesForFrontEnd::convertToArticleFindAllRequest).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(articlesConverted);
    }

    @GetMapping(path = "/{articleId}")
    public ResponseEntity<ArticleFindByIdRequest> findById(@PathVariable Long articleId)
    {
        var article = articleService.findById(articleId);
        var articleDTO = convertArticlesForFrontEnd.convertToArticleFindByIdRequest(article);
        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping(path = "comments/{articleId}")
    public ResponseEntity<GetCommentsForArticle> findCommentsForArticle(@PathVariable Long articleId)
    {
        var article = articleService.findById(articleId);
        var commentsAndUsers = convertArticlesForFrontEnd.getCommentsForArticle(article);
        return ResponseEntity.ok().body(commentsAndUsers);
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

    @GetMapping(path = "/filter/{filterId}")
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

    @GetMapping(path = "/tag/{tagId}")
    public ResponseEntity<Iterable<ArticleDTO>> findByTag(@PathVariable Long tagId)
    {
        var articles = articleService.findByTag(tagId);
        var articlesDTO =
                articles.stream().
                map(ArticleMapper.INSTANCE::toArticleDTO).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(articlesDTO);
    }

    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<Iterable<ArticleDTO>> findByCategory(@PathVariable Long categoryId)
    {
        var articles = articleService.findByCategory(categoryId);
        var articlesDTO =
                articles.stream().
                        map(ArticleMapper.INSTANCE::toArticleDTO).
                        collect(Collectors.toList());
        return ResponseEntity.ok().body(articlesDTO);
    }

    @PutMapping(path = "/favorites/{userId}/{articleId}")
    public ResponseEntity<UserDTO> addFavorites
            (@PathVariable Long userId, @PathVariable Long articleId)
    {
        var updatedUser = articleService.addFavorites(userId, articleId);
        var updatedUserDTO = UserMapper.INSTANCE.toUserDTO(updatedUser);
        return ResponseEntity.ok().body(updatedUserDTO);
    }

    @GetMapping(path = "/title")
    public ResponseEntity<ArticleDTO> findByTitle(@RequestParam String title)
    {
        var article = articleService.findByTitle(title);
        var articleDTO = ArticleMapper.INSTANCE.toArticleDTO(article);
        return ResponseEntity.ok().body(articleDTO);
    }

    @GetMapping(path = "/by")
    public ResponseEntity<Iterable<ArticleFindAllRequest>> findByUsername(@RequestParam String username)
    {
        var articles = articleService.findByUsername(username);
        var articlesDTO =
                articles.
                stream().
                map(convertArticlesForFrontEnd::convertToArticleFindAllRequest).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(articlesDTO);
    }

}
