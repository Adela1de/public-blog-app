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

@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Getter
@Setter
@Table(name = "tb_tag")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @EqualsAndHashCode.Exclude
    private String text;
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Article> articles = new ArrayList<>();

    public Tag(String text) { this.text = text; }
}
