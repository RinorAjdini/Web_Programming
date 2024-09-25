package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    void addAuthorsToBook(List<String>lista,String isbn);
    Optional<Book> findById(Long id);
    void deleteById(Long id);
    Optional<Book> save(String isbn,String title,String genre,int year,Long bookStoreId);

    void deleteBookByID(Long id);
    void addNewBook(String isbn, String title, String genre, int year, Long idStore);
}
