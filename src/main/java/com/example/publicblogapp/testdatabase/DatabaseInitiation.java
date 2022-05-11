package com.example.publicblogapp.testdatabase;

import com.example.publicblogapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatabaseInitiation {

    private final UserRepository userRepository;

    public void initDB()
    {

    }
}
