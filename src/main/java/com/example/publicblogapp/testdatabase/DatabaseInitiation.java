package com.example.publicblogapp.testdatabase;

import com.example.publicblogapp.model.entities.*;
import com.example.publicblogapp.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseInitiation {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final FilterRepository filterRepository;
    private final CommentRepository commentRepository;

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

        var tag1 = new Tag("Historia");
        var tag2 = new Tag("Ciencias da natureza");
        var tag3 = new Tag("Geografia");

        var filter1 = new Filter("Adelaide");
        var filter2 = new Filter("Joao");
        var filter3 = new Filter("Nathalia");

        var com1 = new Comment("WOW!", user1, art1);
        var com2 = new Comment("SUPER COOL!", user1, art2);
        var com3= new Comment("No one cares!", user3, art3);
        var com4 = new Comment("Very nice!", user4, art3);

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
        articleRepository.saveAll(Arrays.asList(art1, art2, art3, art4, art5, art6));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        tagRepository.saveAll(Arrays.asList(tag1, tag2, tag3));
        filterRepository.saveAll(Arrays.asList(filter1, filter2,filter3));
        commentRepository.saveAll(Arrays.asList(com1, com2, com3, com4));

        art1.getCategories().addAll(Arrays.asList(cat1, cat2, cat3));
        art2.getCategories().add(cat1);
        art3.getCategories().add(cat2);
        art4.getCategories().add(cat3);
        art5.getCategories().add(cat2);
        art6.getCategories().addAll(Arrays.asList(cat1, cat2, cat3));

        art1.getTags().addAll(Arrays.asList(tag1, tag2, tag3));
        art2.getTags().add(tag3);
        art3.getTags().add(tag1);
        art4.getTags().add(tag2);
        art5.getTags().add(tag3);
        art6.getTags().addAll(Arrays.asList(tag1, tag2, tag3));

        art3.getFilters().add(filter2);
        art4.getFilters().add(filter2);
        art1.getFilters().add(filter1);
        art2.getFilters().add(filter3);

        user1.getFavorites().addAll(Arrays.asList(art1, art2, art3, art4));
        user2.getFavorites().add(art5);
        user3.getFavorites().add(art6);
        user4.getFavorites().add(art2);
        user5.getFavorites().add(art5);

        articleRepository.saveAll(Arrays.asList(art1, art2, art3, art4, art5, art6));
        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

    }
}
