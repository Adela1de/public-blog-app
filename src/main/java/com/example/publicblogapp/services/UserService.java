package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Article;
import com.example.publicblogapp.model.entities.User;
import com.example.publicblogapp.model.entities.VerificationToken;
import com.example.publicblogapp.repositories.UserRepository;
import com.example.publicblogapp.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

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

    public void saveVerificationTokenForUser(User user, String token)
    {
        var verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }

    public User registerUser(User user)
    {
        var encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setRole("USER");
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return user;
    }

    public String validateVerificationToken(String token)
    {
        var verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null) return "invalid";

        var user = verificationToken.getUser();
        var cal = Calendar.getInstance();

        if( (verificationToken.getExpirationTime().getTime() - cal.getTime().getTime()) <= 0)
        {
            verificationTokenRepository.delete(verificationToken);
            return "Expired!";
        }
        user.setEnabled(true);
        userRepository.save(user);

        return "valid";
    }
}
