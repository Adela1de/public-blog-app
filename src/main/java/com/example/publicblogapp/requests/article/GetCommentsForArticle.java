package com.example.publicblogapp.requests.article;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCommentsForArticle {

    private List<String> Comments;
    private List<String> CommentedBy;
}
