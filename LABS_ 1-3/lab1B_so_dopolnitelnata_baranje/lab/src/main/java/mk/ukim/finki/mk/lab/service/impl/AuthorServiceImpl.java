package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.repository.AuthorRepository;
import mk.ukim.finki.mk.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow();
    }
}
