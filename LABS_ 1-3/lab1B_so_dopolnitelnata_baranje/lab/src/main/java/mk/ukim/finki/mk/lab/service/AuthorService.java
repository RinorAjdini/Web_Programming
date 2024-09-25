package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAuthors();
    Author findById(Long id);
}
