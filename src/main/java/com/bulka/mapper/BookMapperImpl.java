package com.bulka.mapper;

import com.bulka.dto.BookDTO;
import com.bulka.entity.Author;
import com.bulka.entity.Book;
import com.bulka.exception.NotFoundException;
import com.bulka.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookMapperImpl implements BookMapper {

    @Autowired
    private AuthorService authorService;

    @Override
    public BookDTO toDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setAuthor(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());

        // Разделение имени и фамилии автора
        String[] authorParts = bookDTO.getAuthor().split(" ");
        String name = authorParts[0];
        String surname = authorParts.length > 1 ? authorParts[1] : "";

        Author author = authorService.getAuthorByNameAndSurname(name, surname);
        if (author == null) {
            throw new NotFoundException("Author not found: " + bookDTO.getAuthor());
        }
        book.setAuthor(author);

        return book;
    }
    @Override
    public List<BookDTO> toDtos(List<Book> books) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        for (Book book : books) {
            bookDTOS.add(toDto(book));
        }
        return bookDTOS;
    }

    @Override
    public List<Book> toEntities(List<BookDTO> bookDTOS) {
        List<Book> books = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOS) {
            books.add(toEntity(bookDTO));
        }
        return books;
    }
}
