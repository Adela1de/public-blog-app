package com.example.publicblogapp.requests.article;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticlePutRequestBody {

    private String title;
    private String text;
}
