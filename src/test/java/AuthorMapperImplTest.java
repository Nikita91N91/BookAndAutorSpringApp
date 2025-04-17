import com.bulka.dto.AuthorDTO;
import com.bulka.entity.Author;
import com.bulka.mapper.AuthorMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorMapperImplTest {

    private AuthorMapperImpl authorMapper;

    @Before
    public void setup() {
        authorMapper = new AuthorMapperImpl();
    }

    @Test
    public void testToDto() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Author Name");
        author.setSurname("Author Surname");

        AuthorDTO authorDTO = authorMapper.toDto(author);
        assertNotNull(authorDTO);
        assertEquals((Object) 1L, authorDTO.getId());
        assertEquals((Object) "Author Name", authorDTO.getName());
        assertEquals((Object) "Author Surname", authorDTO.getSurname());
    }

    @Test
    public void testToEntity() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Author Name");
        authorDTO.setSurname("Author Surname");

        Author author = authorMapper.toEntity(authorDTO);
        assertNotNull(author);
        assertEquals((Object) 1L, author.getId());
        assertEquals((Object) "Author Name", author.getName());
        assertEquals((Object) "Author Surname", author.getSurname());
    }

    @Test
    public void testToDtos() {
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author();
        author1.setId(1L);
        author1.setName("Author Name 1");
        author1.setSurname("Author Surname 1");
        authors.add(author1);

        Author author2 = new Author();
        author2.setId(2L);
        author2.setName("Author Name 2");
        author2.setSurname("Author Surname 2");
        authors.add(author2);

        List<AuthorDTO> authorDTOS = authorMapper.toDtos(authors);
        assertNotNull(authorDTOS);
        assertEquals((Object) 2, authorDTOS.size());

        AuthorDTO authorDTO1 = authorDTOS.get(0);
        assertEquals((Object) 1L, authorDTO1.getId());
        assertEquals((Object) "Author Name 1", authorDTO1.getName());
        assertEquals((Object) "Author Surname 1", authorDTO1.getSurname());

        AuthorDTO authorDTO2 = authorDTOS.get(1);
        assertEquals((Object) 2L, authorDTO2.getId());
        assertEquals((Object) "Author Name 2", authorDTO2.getName());
        assertEquals((Object) "Author Surname 2", authorDTO2.getSurname());
    }

    @Test
    public void testToEntities() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        AuthorDTO authorDTO1 = new AuthorDTO();
        authorDTO1.setId(1L);
        authorDTO1.setName("Author Name 1");
        authorDTO1.setSurname("Author Surname 1");
        authorDTOS.add(authorDTO1);

        AuthorDTO authorDTO2 = new AuthorDTO();
        authorDTO2.setId(2L);
        authorDTO2.setName("Author Name 2");
        authorDTO2.setSurname("Author Surname 2");
        authorDTOS.add(authorDTO2);

        List<Author> authors = authorMapper.toEntities(authorDTOS);
        assertNotNull(authors);
        assertEquals((Object) 2, authors.size());

        Author author1 = authors.get(0);
        assertEquals((Object) 1L, author1.getId());
        assertEquals((Object) "Author Name 1", author1.getName());
        assertEquals((Object) "Author Surname 1", author1.getSurname());

        Author author2 = authors.get(1);
        assertEquals((Object) 2L, author2.getId());
        assertEquals((Object) "Author Name 2", author2.getName());
        assertEquals((Object) "Author Surname 2", author2.getSurname());
    }
}
