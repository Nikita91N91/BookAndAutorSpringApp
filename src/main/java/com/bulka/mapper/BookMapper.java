package com.bulka.mapper;

import com.bulka.dto.BookDTO;
import com.bulka.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO toDto(Book book);
    Book toEntity(BookDTO bookDTO);
    List<BookDTO> toDtos(List<Book> books);
    List<Book> toEntities(List<BookDTO> bookDTOS);
}
