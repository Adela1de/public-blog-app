package com.example.publicblogapp.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "tb_comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_commented;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article_commented;

    public Comment(String text, User user_commented, Article article_commented)
    {
        this.text = text;
        this.user_commented = user_commented;
        this.article_commented = article_commented;
    }
}
