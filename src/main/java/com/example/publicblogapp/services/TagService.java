package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Tag;
import com.example.publicblogapp.repositories.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> findAll(){ return tagRepository.findAll(); }

    public Tag findById(Long id){ return findByIdOrElseThrowObjectNotFoundException(id); }

    public Tag findByIdOrElseThrowObjectNotFoundException(long id)
    {
        return tagRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("There is no Tag with id: "+id));
    }
}
