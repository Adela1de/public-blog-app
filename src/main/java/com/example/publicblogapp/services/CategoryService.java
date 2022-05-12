package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Category;
import com.example.publicblogapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){ return categoryRepository.findAll(); }

    public Category findById(Long id){ return findByIdOrElseThrowObjectNotFoundException(id); }

    public Category findByIdOrElseThrowObjectNotFoundException(Long id)
    {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("There is no Category with id: "+id));
    }

}
