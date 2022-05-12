package com.example.publicblogapp.mappers;

import com.example.publicblogapp.dtos.category.CategoryDTO;
import com.example.publicblogapp.model.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public abstract CategoryDTO toCategoryDTO(Category category);
}
