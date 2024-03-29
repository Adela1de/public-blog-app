package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.Comment;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.repositories.ArticleRepository;
import com.example.publicblogapp.repositories.CommentRepository;
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
    private final TagService tagService;
    private final CommentRepository commentRepository;

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

    public List<Article> findByFilter(Long filterId)
    {
        var filter = filterService.findById(filterId);
        List<Article> filteredArticles = new ArrayList<>();
        findAll().stream().forEach(art -> {
            if(art.getFilters().contains(filter)) filteredArticles.add(art);
        });

        return filteredArticles;
    }

    public List<Article> findByTag(Long tagId)
    {
        var tag = tagService.findById(tagId);
        List<Article> articles = new ArrayList<>();
        findAll().stream().forEach(art -> {
            if(art.getTags().contains(tag)) articles.add(art);
                }
        );
        return articles;
    }

    public List<Article> findByCategory(Long categoryId)
    {
        var category = tagService.findById(categoryId);
        List<Article> articles = new ArrayList<>();
        findAll().stream().forEach(art -> {
                    if(art.getTags().contains(category)) articles.add(art);
                }
        );
        return articles;
    }

    public Article findByTitle(String title) {
        var article = articleRepository.findByTitle(title).orElseThrow(
                () -> new ObjectNotFoundException("There is no article with title: "+ title));
        return article;
    }

    public List<Article> findByUsername(String username)
    {
        var articles = articleRepository.findByUsername(username);
        return articles;
    }

    public User addFavorites(Long userId, Long articleId)
    {
        var user = userService.findByIdOrElseThrowObjectNotFoundException(userId);
        var article = findArticleByIdOrElseThrowObjectNotFoundException(articleId);
        user.getFavorites().add(article);
        var updatedUser = userService.updateOrSaveUser(user);
        return updatedUser;
    }

    public Comment addComment(Long id, Comment comment)
    {
        findArticleByIdOrElseThrowObjectNotFoundException(id);
        userService.findByIdOrElseThrowObjectNotFoundException(comment.getUser_commented().getId());
        var savedComment = commentRepository.save(comment);
        return savedComment;
    }

}
