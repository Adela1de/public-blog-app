package com.example.publicblogapp.model.entities;

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
    private String passWord;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    public User(String userName, String email, String passWord)
    {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
    }

}
