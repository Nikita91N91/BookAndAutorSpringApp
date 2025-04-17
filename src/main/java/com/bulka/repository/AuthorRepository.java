package com.bulka.repository;

import com.bulka.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByNameAndSurname(String name, String surname);
}
