import com.bulka.controller.BookController;
import com.bulka.dto.BookDTO;
import com.bulka.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO;

    @Before
    public void setup() {
        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Book Title");
        bookDTO.setDescription("Book Description");
    }

    @Test
    public void testGetAllBooks() {
        List<BookDTO> books = new ArrayList<>();
        books.add(bookDTO);
        when(bookService.getAllBooks()).thenReturn(books);
        List<BookDTO> result = bookController.getAllBooks().getBody();
        assertNotNull(result);
        assertEquals((Object) 1, result.size());
    }

    @Test
    public void testGetBookById() {
        when(bookService.getBookById(anyLong())).thenReturn(bookDTO);
        BookDTO result = bookController.getBookById(1L).getBody();
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookService.getBookById(anyLong())).thenReturn(null);
        assertEquals(HttpStatus.NOT_FOUND, bookController.getBookById(1L).getStatusCode());
    }

    @Test
    public void testCreateBook() {
        when(bookService.createBook(any(BookDTO.class))).thenReturn(bookDTO);
        BookDTO result = bookController.createBook(bookDTO).getBody();
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
    }

    @Test
    public void testUpdateBook() {
        when(bookService.updateBook(any(BookDTO.class))).thenReturn(bookDTO);
        BookDTO result = bookController.updateBook(1L, bookDTO).getBody();
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
    }

    @Test
    public void testDeleteBook() {
        doNothing().when(bookService).deleteBook(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, bookController.deleteBook(1L).getStatusCode());
    }
}
