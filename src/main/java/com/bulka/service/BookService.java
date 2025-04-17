package com.bulka.service;

import com.bulka.dto.BookDTO;
import com.bulka.entity.Author;
import com.bulka.entity.Book;
import com.bulka.exception.NotFoundException;
import com.bulka.mapper.BookMapper;
import com.bulka.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {


    private final AuthorService authorService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper,AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorService = authorService;
    }


    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDtos(books);
    }

    @Transactional(readOnly = true)
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return bookMapper.toDto(book);
    }

    public BookDTO createBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("BookDTO cannot be null");
        }
        Book book = bookMapper.toEntity(bookDTO);
        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        if (bookDTO == null) {
            throw new IllegalArgumentException("BookDTO cannot be null");
        }
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> new NotFoundException("Book not found"));
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());

        // Разделяем имя и фамилию автора
        String[] authorParts = bookDTO.getAuthor().split("\\s+");
        if (authorParts.length < 2) {
            throw new IllegalArgumentException("Author name must contain first name and last name separated by space.");
        }
        String name = authorParts[0];
        String surname = authorParts[1];

        Author author = authorService.getAuthorByNameAndSurname(name, surname);
        if (author == null) {
            throw new NotFoundException("Author not found");
        }
        book.setAuthor(author);

        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}