package com.example.publicblogapp.controllers;

import com.example.publicblogapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;


}
