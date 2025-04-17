package com.bulka.mapper;

import com.bulka.dto.AuthorDTO;
import com.bulka.entity.Author;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorMapperImpl implements AuthorMapper {
    @Override
    public AuthorDTO toDto(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setSurname(author.getSurname());
        return authorDTO;
    }

    @Override
    public Author toEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());
        return author;
    }

    @Override
    public List<AuthorDTO> toDtos(List<Author> authors) {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        for (Author author : authors) {
            authorDTOS.add(toDto(author));
        }
        return authorDTOS;
    }

    @Override
    public List<Author> toEntities(List<AuthorDTO> authorDTOS) {
        List<Author> authors = new ArrayList<>();
        for (AuthorDTO authorDTO : authorDTOS) {
            authors.add(toEntity(authorDTO));
        }
        return authors;
    }
}
