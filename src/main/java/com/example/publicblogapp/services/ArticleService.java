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
        var user =
                userRepository.
                findById(userId).
                orElseThrow(() -> new RuntimeException("This user does not exist!"));
        article.setUser(user);

        return articleRepository.save(article);
    }
}
