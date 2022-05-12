package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserService userService;
    private final FilterService filterService;

    public List<Article> findAll(){ return articleRepository.findAll(); }

    public Article findById(Long articleId)
    {
        return findArticleByIdOrElseThrowObjectNotFoundException(articleId);
    }

    public List<Article> findByUser(Long userId)
    {
        var user = userService.findByIdOrElseThrowObjectNotFoundException(userId);
        return user.getArticles();
    }

    public Article findByUserAndId(Long userId, Long articleId)
    {
        var user = userService.findByIdOrElseThrowObjectNotFoundException(userId);
        var article = findArticleByIdOrElseThrowObjectNotFoundException(articleId);

        if(user.getArticles().contains(article)) return article;
        else
        {
            throw new ObjectNotFoundException
            ("Article with id of: "+articleId+" does not exist for user with id of: "+ userId);
        }
    }

    public Article findArticleByIdOrElseThrowObjectNotFoundException(Long articleId)
    {
        return articleRepository.
                findById(articleId).
                orElseThrow(() -> new ObjectNotFoundException
                        ("Article with id: "+articleId+" does not exist for classType: "+ Article.class) );
    }

    public Article createArticle(Article article, Long userId)
    {
        var user = userService.findByIdOrElseThrowObjectNotFoundException(userId);
        article.setUser(user);

        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article article)
    {
        var art = findArticleByIdOrElseThrowObjectNotFoundException(id);
        article.setId(id);
        article.setUser(art.getUser());
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id)
    {
        articleRepository.deleteById(id);
    }

    public List<Article> findByFilter(Long id)
    {
        var filter = filterService.findById(id);
        var articles = findAll();
        List<Article> filteredArticles = new ArrayList<>();
        articles.stream().forEach(art -> {
            if(art.getFilters().contains(filter)) filteredArticles.add(art);
        });

        return filteredArticles;
    }
}
