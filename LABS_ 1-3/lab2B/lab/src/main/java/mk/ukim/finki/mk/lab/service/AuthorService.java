package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
    Optional<Author> saveAuthor(String name, String surname, String bio);
}
