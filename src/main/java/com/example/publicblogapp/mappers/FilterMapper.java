package com.example.publicblogapp.mappers;

import com.example.publicblogapp.dtos.filter.FilterDTO;
import com.example.publicblogapp.model.entities.Filter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class FilterMapper {

    public static final FilterMapper INSTANCE = Mappers.getMapper(FilterMapper.class);

    public abstract FilterDTO toFilterDTO(Filter filter);
}
