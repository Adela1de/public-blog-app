package com.example.publicblogapp.testdatabase;

import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DatabaseInitiation {

    private final UserRepository userRepository;

    public void initDB()
    {
        var user1 = new User("Adelaide", "Luiz@Adelaide.com", "3104");
        var user2 = new User("Nathalia", "Nathalia@Adelaide.com", "3104");
        var user3 = new User("Joao", "Joao@Leal.com", "6666");
        var user4 = new User("Rogerio", "Rogerin@22.com", "1111");
        var user5 = new User("Aline", "Aline@Silva.com", "7777");

        userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));
    }
}
