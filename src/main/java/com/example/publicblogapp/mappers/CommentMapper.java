package com.example.publicblogapp.mappers;

import com.example.publicblogapp.model.entities.Comment;
import com.example.publicblogapp.requests.comments.CommentPostRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    public static final CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    public abstract Comment toComment(CommentPostRequestBody commentPostRequestBody);
}
