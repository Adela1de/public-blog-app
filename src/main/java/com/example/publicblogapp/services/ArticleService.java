package com.example.publicblogapp.services;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.repositories.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private ArticleRepository articleRepository;

    public List<Article> findAll(){ return articleRepository.findAll(); }
}
