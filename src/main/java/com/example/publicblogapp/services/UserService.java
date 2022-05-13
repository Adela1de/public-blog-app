package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Article;
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

    public User findById(Long id){ return findByIdOrElseThrowObjectNotFoundException(id); }

    public User create(User user){ return updateOrSaveUser(user); }

    public User findByIdOrElseThrowObjectNotFoundException(Long id)
    {
        return userRepository.
                findById(id).
                orElseThrow(() ->
                        new ObjectNotFoundException("User with id: "+id+" does not exist for classType: "+ User.class));
    }

    public List<Article> findFavorites(Long id)
    {
        var user = findByIdOrElseThrowObjectNotFoundException(id);
        return user.getFavorites();
    }

    public User updateUser(User user)
    {
        findByIdOrElseThrowObjectNotFoundException(user.getId());
        return updateOrSaveUser(user);
    }

    public User updateOrSaveUser(User user)
    {
        return userRepository.save(user);
    }
}
