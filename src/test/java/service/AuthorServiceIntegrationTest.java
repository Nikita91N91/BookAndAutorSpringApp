package service;

import com.bulka.config.TestConfig;
import com.bulka.entity.Author;
import com.bulka.service.AuthorService;
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
public class AuthorServiceIntegrationTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testSaveAndRetrieveAuthor() {
        Author author = new Author();
        author.setName("Test");
        author.setSurname("Author");

        Author saved = authorService.saveAuthor(author);
        Author found = authorService.getAuthorById(saved.getId());

        assertEquals(saved.getName(), found.getName());
        assertEquals(saved.getSurname(), found.getSurname());
    }
}