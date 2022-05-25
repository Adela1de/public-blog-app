package com.example.publicblogapp.repositories;

import com.example.publicblogapp.model.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByTitle(String title);

    @Query(value = "select a.* from tb_article a " +
            "inner join tb_user u where " +
            "u.id = a.user_id and " +
            "u.user_name like ?", nativeQuery = true)
    List<Article> findByUsername(String username);
}
