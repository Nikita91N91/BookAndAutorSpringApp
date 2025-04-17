package com.bulka.mapper;

import com.bulka.dto.AuthorDTO;
import com.bulka.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO toDto(Author author);

    Author toEntity(AuthorDTO authorDTO);

    List<AuthorDTO> toDtos(List<Author> authors);

    List<Author> toEntities(List<AuthorDTO> authorDTOS);
}