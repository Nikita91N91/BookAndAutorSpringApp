import com.bulka.dto.BookDTO;
import com.bulka.entity.Author;
import com.bulka.entity.Book;
import com.bulka.mapper.BookMapper;
import com.bulka.repository.BookRepository;
import com.bulka.service.AuthorService;
import com.bulka.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDTO bookDTO;
    private Author author;

    @Before
    public void setup() {
        // Инициализация тестовых данных
        author = new Author();
        author.setId(1L);
        author.setName("John");
        author.setSurname("Doe");

        book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setDescription("Book Description");
        book.setAuthor(author);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Book Title");
        bookDTO.setDescription("Book Description");
        bookDTO.setAuthor("John Doe");
    }

    @Test
    public void testGetAllBooks() {
        // Подготовка
        List<Book> books = new ArrayList<>();
        books.add(book);

        List<BookDTO> bookDTOs = new ArrayList<>();
        bookDTOs.add(bookDTO);

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toDtos(books)).thenReturn(bookDTOs);

        // Вызов
        List<BookDTO> result = bookService.getAllBooks();

        // Проверка
        assertEquals(1, result.size());
        assertEquals("Book Title", result.get(0).getTitle());
    }

    @Test
    public void testGetBookById() {
        // Подготовка
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        // Вызов
        BookDTO result = bookService.getBookById(1L);

        // Проверка
        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    public void testCreateBook() {
        // Подготовка
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        // Вызов
        BookDTO result = bookService.createBook(bookDTO);

        // Проверка
        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    public void testUpdateBook() {
        // Подготовка
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(authorService.getAuthorByNameAndSurname("John", "Doe")).thenReturn(author);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDTO);

        // Вызов
        BookDTO result = bookService.updateBook(bookDTO);

        // Проверка
        assertNotNull(result);
        assertEquals("Book Title", result.getTitle());
    }

    @Test
    public void testDeleteBook() {
        // Подготовка
        when(bookRepository.existsById(1L)).thenReturn(true);

        // Вызов
        bookService.deleteBook(1L);

        // Проверка
        verify(bookRepository, times(1)).deleteById(1L);
    }
}