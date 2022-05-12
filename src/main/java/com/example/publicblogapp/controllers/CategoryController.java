package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.category.CategoryDTO;
import com.example.publicblogapp.mappers.CategoryMapper;
import com.example.publicblogapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Iterable<CategoryDTO>> getAll()
    {
        var categories = categoryService.findAll();
        var categoriesDTO =
                categories.
                stream().
                map(CategoryMapper.INSTANCE::toCategoryDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(categoriesDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDTO>  findById(@PathVariable Long id)
    {
        var category = categoryService.findById(id);
        var categoryDTO = CategoryMapper.INSTANCE.toCategoryDTO(category);
        return ResponseEntity.ok().body(categoryDTO);
    }

}
