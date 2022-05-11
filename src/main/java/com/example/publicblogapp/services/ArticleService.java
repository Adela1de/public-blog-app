package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
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
        var user = findUserByIdOrElseThrowObjectNotFoundException(userId);
        article.setUser(user);

        return articleRepository.save(article);
    }

    public Article findById(Long articleId)
    {
        return findArticleByIdOrElseThrowObjectNotFoundException(articleId);
    }

    public List<Article> findByUser(Long userId)
    {
        var user = findUserByIdOrElseThrowObjectNotFoundException(userId);
        return user.getArticles();
    }

    public Article findByUserAndId(Long userId, Long articleId)
    {
        var user = findUserByIdOrElseThrowObjectNotFoundException(userId);
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

    public User findUserByIdOrElseThrowObjectNotFoundException(Long userId)
    {
        return userRepository.
                findById(userId).
                orElseThrow(() -> new ObjectNotFoundException
                        ("User with id: "+userId+" does not exist for classType: "+ User.class));
    }
}
