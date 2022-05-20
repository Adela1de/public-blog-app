package com.example.publicblogapp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @EqualsAndHashCode.Exclude
    private String userName;
    @EqualsAndHashCode.Exclude
    private String email;
    @EqualsAndHashCode.Exclude
    private String password;
    @EqualsAndHashCode.Exclude
    private String role;
    @EqualsAndHashCode.Exclude
    private boolean enabled;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_user_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private List<Article> favorites = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user_commented")
    private List<Comment> comments = new ArrayList<>();

    public User(String userName, String email, String password)
    {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

}
