package com.example.publicblogapp.mappers.impl;

import com.example.publicblogapp.model.entities.Comment;
import com.example.publicblogapp.requests.comments.CommentConversionToFrontEnd;
import org.springframework.stereotype.Component;

@Component
public class ConvertCommentsForFrontEnd {

    public CommentConversionToFrontEnd convertToFrontEnd(Comment comment)
    {
        var converted = new CommentConversionToFrontEnd();

        converted.setComment(comment.getText());
        converted.setCommentedBy(comment.getUser_commented().getUserName());

        return converted;
    }
}
