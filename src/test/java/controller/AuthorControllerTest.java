package controller;

import com.bulka.controller.AuthorController;
import com.bulka.dto.AuthorDTO;
import com.bulka.entity.Author;
import com.bulka.mapper.AuthorMapper;
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
public class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorController authorController;

    private Author author;
    private AuthorDTO authorDTO;

    @Before
    public void setup() {
        author = new Author();
        author.setId(1L);
        author.setName("Author Name");
        author.setSurname("Author Surname");

        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Author Name");
        authorDTO.setSurname("Author Surname");
    }

    @Test
    public void testGetAllAuthors() {
        List<Author> authors = new ArrayList<>();
        authors.add(author);
        when(authorService.getAllAuthors()).thenReturn(authors);
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        authorDTOS.add(authorDTO);
        when(authorMapper.toDtos(any(List.class))).thenReturn(authorDTOS);
        List<AuthorDTO> result = authorController.getAllAuthors();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void testCreateAuthor() {
        when(authorMapper.toEntity(any(AuthorDTO.class))).thenReturn(author);
        when(authorService.saveAuthor(any(Author.class))).thenReturn(author);
        when(authorMapper.toDto(any(Author.class))).thenReturn(authorDTO);
        AuthorDTO result = authorController.createAuthor(authorDTO);
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
    }

    @Test
    public void testGetAuthorById() {
        when(authorService.getAuthorById(anyLong())).thenReturn(author);
        when(authorMapper.toDto(any(Author.class))).thenReturn(authorDTO);
        AuthorDTO result = authorController.getAuthorById(1L).getBody();
        assertNotNull(result);
        assertEquals((Object) 1L, result.getId());
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        when(authorService.getAuthorById(anyLong())).thenReturn(null);
        assertEquals(org.springframework.http.HttpStatus.NOT_FOUND, authorController.getAuthorById(1L).getStatusCode());
    }

    @Test
    public void testDeleteAuthor() {
        doNothing().when(authorService).deleteAuthor(anyLong());
        assertEquals(org.springframework.http.HttpStatus.NO_CONTENT, authorController.deleteAuthor(1L).getStatusCode());
    }
}
