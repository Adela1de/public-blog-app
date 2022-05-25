package com.example.publicblogapp.mappers.impl;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.model.entities.Comment;
import com.example.publicblogapp.requests.article.ArticleFindAllRequest;
import com.example.publicblogapp.requests.article.ArticleFindByIdRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ConvertArticlesForFrontEnd {

    public ArticleFindAllRequest convertToArticleFindAllRequest(Article article)
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

    public ArticleFindByIdRequest convertToArticleFindByIdRequest(Article article)
    {
        var articleFindByIdRequest = new ArticleFindByIdRequest();
        var listOfComments = new ArrayList<String>();
        var listOfUsernames = new ArrayList<String>();
        articleFindByIdRequest.setId(article.getId());
        articleFindByIdRequest.setTitle(article.getTitle());
        articleFindByIdRequest.setUsername(article.getUser().getUserName());
        if(article.getComments() != null)
        {
            for(Comment com: article.getComments())
            {
                listOfComments.add(com.getText());
                listOfUsernames.add(com.getUser_commented().getUserName());
            }
            articleFindByIdRequest.setComments(listOfComments);
            articleFindByIdRequest.setCommentedBy(listOfUsernames);
        }

        return articleFindByIdRequest;
    }
}
