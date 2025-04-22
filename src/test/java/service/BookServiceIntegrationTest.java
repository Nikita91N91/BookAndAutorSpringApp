package service;

import com.bulka.config.TestConfig;
import com.bulka.dto.BookDTO;
import com.bulka.entity.Author;
import com.bulka.service.AuthorService;
import com.bulka.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
@Rollback
public class BookServiceIntegrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testCreateBookWithAuthor() {
        Author author = new Author();
        author.setName("John");
        author.setSurname("Doe");
        authorService.saveAuthor(author);

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test Book");
        bookDTO.setDescription("Test Description");
        bookDTO.setAuthor("John Doe");

        BookDTO created = bookService.createBook(bookDTO);
        assertNotNull(created.getId());
        assertEquals("Test Book", created.getTitle());
    }
}