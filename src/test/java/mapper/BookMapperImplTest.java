package mapper;

import com.bulka.dto.BookDTO;
import com.bulka.entity.Author;
import com.bulka.entity.Book;
import com.bulka.exception.NotFoundException;
import com.bulka.mapper.BookMapperImpl;
import com.bulka.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookMapperImplTest {

    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookMapperImpl bookMapper;

    private Book book;
    private BookDTO bookDTO;
    private Author author;

    @Before
    public void setup() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Book Title");
        book.setDescription("Book Description");

        author = new Author();
        author.setId(1L);
        author.setName("Author Name");
        author.setSurname("Author Surname");
        book.setAuthor(author);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Book Title");
        bookDTO.setDescription("Book Description");
        bookDTO.setAuthor("Author Name Author Surname");
    }

    @Test
    public void testToDto() {
        BookDTO result = bookMapper.toDto(book);
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
        assertEquals((Object) "Book Title", result.getTitle());
        assertEquals((Object) "Book Description", result.getDescription());
        assertEquals((Object) "Author Name Author Surname", result.getAuthor());
    }

    @Test
    public void testToEntity() {
        when(authorService.getAuthorByNameAndSurname(anyString(), anyString())).thenReturn(author);
        Book result = bookMapper.toEntity(bookDTO);
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
        assertEquals((Object) "Book Title", result.getTitle());
        assertEquals((Object) "Book Description", result.getDescription());
        assertEquals((Object) author, result.getAuthor());
    }

    @Test(expected = NotFoundException.class)
    public void testToEntityNotFound() {
        when(authorService.getAuthorByNameAndSurname(anyString(), anyString())).thenReturn(null);
        bookMapper.toEntity(bookDTO);
    }

    @Test
    public void testToDtos() {
        List<Book> books = new ArrayList<>();
        books.add(book);
        List<BookDTO> result = bookMapper.toDtos(books);
        assertNotNull(result);
        assertEquals(1, result.size());
        BookDTO bookDTOResult = result.get(0);
        assertEquals((Object) 1L, bookDTOResult.getId());
        assertEquals((Object) "Book Title", bookDTOResult.getTitle());
        assertEquals((Object) "Book Description", bookDTOResult.getDescription());
        assertEquals((Object) "Author Name Author Surname", bookDTOResult.getAuthor());
    }

    @Test
    public void testToEntities() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        bookDTOS.add(bookDTO);
        when(authorService.getAuthorByNameAndSurname(anyString(), anyString())).thenReturn(author);
        List<Book> result = bookMapper.toEntities(bookDTOS);
        assertNotNull(result);
        assertEquals(1, result.size());
        Book bookResult = result.get(0);
        assertEquals((Object) 1L, bookResult.getId());
        assertEquals((Object) "Book Title", bookResult.getTitle());
        assertEquals((Object) "Book Description", bookResult.getDescription());
        assertEquals((Object) author, bookResult.getAuthor());
    }
}