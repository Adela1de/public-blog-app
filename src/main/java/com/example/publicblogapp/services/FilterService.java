package com.example.publicblogapp.services;

import com.example.publicblogapp.exceptions.ObjectNotFoundException;
import com.example.publicblogapp.model.entities.Filter;
import com.example.publicblogapp.repositories.FilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterRepository filterRepository;

    public List<Filter> findAll(){ return filterRepository.findAll(); }

    public Filter findById(Long id){ return findByIdOrElseThrowObjectNotFoundException(id); }

    public Filter findByIdOrElseThrowObjectNotFoundException(Long id)
    {
        return filterRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("There is no Filter with id: "+id));
    }
}
