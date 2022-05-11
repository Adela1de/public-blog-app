package com.example.publicblogapp.services;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.repositories.ArticleRepository;
import com.example.publicblogapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public List<Article> findAll(){ return articleRepository.findAll(); }

    public Article createArticle(Article article, Long userId)
    {
        var user = findUserByIdOrElseThrowRunTimeException(userId);
        article.setUser(user);

        return articleRepository.save(article);
    }

    public Article findById(Long articleId)
    {
        return findArticleByIdOrElseThrowRunTimeException(articleId);
    }

    public List<Article> findByUser(Long userId)
    {
        var user = findUserByIdOrElseThrowRunTimeException(userId);
        return user.getArticles();
    }

    public Article findByUserAndId(Long userId, Long articleId)
    {
        var user = findUserByIdOrElseThrowRunTimeException(userId);
        var article = findArticleByIdOrElseThrowRunTimeException(articleId);

        if(user.getArticles().contains(article)) return article;
        else { throw new RuntimeException("Article does not exist for this user"); }
    }

    public Article findArticleByIdOrElseThrowRunTimeException(Long articleId)
    {
        return articleRepository.
                findById(articleId).
                orElseThrow(() -> new RuntimeException("This article does not exist!") );
    }

    public User findUserByIdOrElseThrowRunTimeException(Long userId)
    {
        return userRepository.
                findById(userId).
                orElseThrow(() -> new RuntimeException("This user does not exist!"));
    }
}
