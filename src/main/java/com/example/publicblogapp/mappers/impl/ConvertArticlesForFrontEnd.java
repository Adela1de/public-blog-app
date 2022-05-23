package com.example.publicblogapp.mappers.impl;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.requests.article.ArticleFindAllRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConvertArticlesForFrontEnd {

    public ArticleFindAllRequest convertToArticleRequest(Article article)
    {
        var articleFindAllRequest = new ArticleFindAllRequest();
        var listOfCats = new ArrayList<String>();
        articleFindAllRequest.setId(article.getId());
        articleFindAllRequest.setTitle(article.getTitle());
        articleFindAllRequest.setUsername(article.getUser().getUserName());
        if(article.getCategories() != null)
        {
            for (Category cat: article.getCategories()) { listOfCats.add(cat.getName()); }
            articleFindAllRequest.setCategories(listOfCats);
        }
        return articleFindAllRequest;
    }
}
