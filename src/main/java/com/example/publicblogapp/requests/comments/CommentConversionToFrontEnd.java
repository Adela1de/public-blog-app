package com.example.publicblogapp.requests.comments;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentConversionToFrontEnd {

    private String comment;
    private String commentedBy;
}
