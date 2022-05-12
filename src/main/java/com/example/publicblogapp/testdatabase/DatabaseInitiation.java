package com.example.publicblogapp.testdatabase;

import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.repositories.ArticleRepository;
import com.example.publicblogapp.repositories.CategoryRepository;
import com.example.publicblogapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseInitiation {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    public void initDB()
    {
        var user1 = new User("Adelaide", "Luiz@Adelaide.com", "3104");
        var user2 = new User("Nathalia", "Nathalia@Adelaide.com", "3104");
        var user3 = new User("Joao", "Joao@Leal.com", "6666");
        var user4 = new User("Rogerio", "Rogerin@22.com", "1111");
        var user5 = new User("Aline", "Aline@Silva.com", "7777");

        var art1 = new Article("Aquecimento Global", "Aquecimento global está acontecendo e é ruim", user1);
        var art2 = new Article("Desigualdade social", "Desigualdade social está acontecendo e é ruim", user2);
        var art3 = new Article("Desmatamento", "Desmatamento está acontecendo e é ruim", user3);
        var art4 = new Article("Bullying", "Bullying está acontecendo e é ruim", user3);
        var art5 = new Article("Racismo", "Racismo está acontecendo e é ruim", user4);
        var art6 = new Article("Tiroteio", "Tiroteio está acontecendo e é ruim", user5);

        var cat1 = new Category("Revisão bibliográfica");
        var cat2 = new Category("Estudo de caso");
        var cat3 = new Category("Pesquisa de ação");

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        articleRepository.saveAll(Arrays.asList(art1, art2, art3, art4, art5, art6));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        art1.getCategories().add(cat1);
        art1.getCategories().add(cat2);
        art1.getCategories().add(cat3);
        art2.getCategories().add(cat1);
        art3.getCategories().add(cat2);
        art4.getCategories().add(cat3);
        art5.getCategories().add(cat2);
        art6.getCategories().add(cat3);
        art6.getCategories().add(cat1);
        art6.getCategories().add(cat2);

        articleRepository.saveAll(Arrays.asList(art1, art2, art3, art4, art5, art6));

    }
}
