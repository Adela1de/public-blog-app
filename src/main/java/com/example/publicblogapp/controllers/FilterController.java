package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.filter.FilterDTO;
import com.example.publicblogapp.mappers.FilterMapper;
import com.example.publicblogapp.services.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/filters")
@CrossOrigin("*")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @GetMapping
    public ResponseEntity<Iterable<FilterDTO>> findAll()
    {
        var filters = filterService.findAll();
        var filtersDTO =
                filters.stream().
                map(FilterMapper.INSTANCE::toFilterDTO).
                collect(Collectors.toList());
        return ResponseEntity.ok().body(filtersDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<FilterDTO> findById(@PathVariable Long id)
    {
        var filter = filterService.findById(id);
        var filterDTO = FilterMapper.INSTANCE.toFilterDTO(filter);
        return ResponseEntity.ok().body(filterDTO);
    }
}
