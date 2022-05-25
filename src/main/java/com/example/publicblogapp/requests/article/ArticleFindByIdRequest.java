package com.example.publicblogapp.requests.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleFindByIdRequest {

    private Long id;
    private String title;
    private String Text;
    private String username;
}
