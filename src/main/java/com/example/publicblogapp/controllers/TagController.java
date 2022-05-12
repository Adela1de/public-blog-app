package com.example.publicblogapp.controllers;

import com.example.publicblogapp.dtos.tag.TagDTO;
import com.example.publicblogapp.mappers.TagMapper;
import com.example.publicblogapp.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tags")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Iterable<TagDTO>> findAll()
    {
        var tags = tagService.findAll();
        var tagsDTO =
                tags.
                stream().
                map(TagMapper.INSTANCE::toTagDTO).
                collect(Collectors.toList());

        return ResponseEntity.ok().body(tagsDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagDTO> findById(@PathVariable Long id)
    {
        var tag = tagService.findById(id);
        var tagDTO = TagMapper.INSTANCE.toTagDTO(tag);
        return ResponseEntity.ok().body(tagDTO);
    }

}
