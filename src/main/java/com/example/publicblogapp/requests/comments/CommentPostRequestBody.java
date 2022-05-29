package com.example.publicblogapp.requests.comments;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPostRequestBody {

    private String text;
    private User user_commented;
    private Article article_commented;

}
