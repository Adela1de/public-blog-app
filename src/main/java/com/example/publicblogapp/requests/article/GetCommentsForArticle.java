package com.example.publicblogapp.requests.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCommentsForArticle {

    private String Comments;
    private String CommentedBy;
}
