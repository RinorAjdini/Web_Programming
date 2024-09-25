package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.BookStore;
import mk.ukim.finki.mk.lab.repository.BookStoreRepository;
import mk.ukim.finki.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }
}
