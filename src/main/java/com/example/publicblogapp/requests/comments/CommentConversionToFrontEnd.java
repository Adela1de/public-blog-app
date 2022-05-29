package com.example.publicblogapp.requests.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentConversionToFrontEnd {

    private String comment;
    private String commentedBy;

}
