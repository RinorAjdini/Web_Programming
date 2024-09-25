package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    void addAuthorsToBook(List<String>lista,String isbn);
}
