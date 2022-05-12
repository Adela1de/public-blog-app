package com.example.publicblogapp.mappers;

import com.example.publicblogapp.dtos.tag.TagDTO;
import com.example.publicblogapp.model.entities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TagMapper {

    public static final TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    public abstract TagDTO toTagDTO(Tag tag);
}
