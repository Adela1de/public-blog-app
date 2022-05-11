package com.example.publicblogapp.services;

import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){ return userRepository.findAll(); }

    public User findById(Long id){ return findByIdOrElseThrowRuntimeException(id); }

    public User create(User user){ return userRepository.save(user); }

    public User findByIdOrElseThrowRuntimeException(Long id)
    {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("This does not exist"));
    }
}
