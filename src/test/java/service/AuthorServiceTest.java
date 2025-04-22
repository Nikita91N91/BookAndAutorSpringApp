package service;

import com.bulka.entity.Author;
import com.bulka.repository.AuthorRepository;
import com.bulka.service.AuthorService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @Before
    public void setup() {
        author = new Author();
        author.setId(1L);
        author.setName("John");
        author.setSurname("Doe");
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorRepository.findAll()).thenReturn(authors);
        List<Author> result = authorService.getAllAuthors();
        assertEquals(1, result.size());
        assertEquals(author, result.get(0));
    }

    @Test
    public void testSaveAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(author);
        Author result = authorService.saveAuthor(author);
        assertEquals(author, result);
    }

    @Test
    public void testGetAuthorById() {
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        Author result = authorService.getAuthorById(1L);
        assertEquals(author, result);
    }

    @Test
    public void testGetAuthorByNameAndSurname() {
        when(authorRepository.findByNameAndSurname(anyString(), anyString())).thenReturn(author);
        Author result = authorService.getAuthorByNameAndSurname("John", "Doe");
        assertEquals(author, result);
    }

    @Test
    public void testDeleteAuthor() {
        authorService.deleteAuthor(1L);
        verify(authorRepository, times(1)).deleteById(1L);
    }
}
