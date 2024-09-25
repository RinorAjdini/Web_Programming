package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;
import mk.ukim.finki.mk.lab.model.exceptions.BookStoreNotFound;
import mk.ukim.finki.mk.lab.repository.AuthorRepository;
import mk.ukim.finki.mk.lab.repository.BookRepository;
import mk.ukim.finki.mk.lab.repository.BookStoreRepository;
import mk.ukim.finki.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).orElse(null);
        Book b = bookRepository.findByIsbn(isbn);
        if(b==null || author==null)
            return null;
        bookRepository.addAuthorToBook(author,b);
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return this.bookRepository.findByIsbn(isbn);
    }

    @Override
    public void addAuthorsToBook(List<String> lista,String isbn) {
        Book b = bookRepository.findByIsbn(isbn);
        if(b==null || lista==null)
            return;

        for(String id :  lista){
            Author d = DataHolder.authors.get(Integer.parseInt(id)-1);
            bookRepository.addAuthorToBook(d,b);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> save(String isbn, String title, String genre, int year, Long bookStoreId) {
        BookStore bookStore = this.bookStoreRepository.findById(bookStoreId).orElseThrow(()->new BookStoreNotFound(bookStoreId));
        return this.bookRepository.save(isbn,title,genre,year,bookStore);
    }

    @Override
    public void deleteBookByID(Long id) {
        this.bookRepository.findAll().removeIf(i->i.getId().equals(id));
    }
    @Override
    public void addNewBook(String isbn, String title, String genre, int year, Long idStore) {
        if (title.equals("") || isbn.equals("") || genre.equals("") || year==0 )
            return;
        Book newBook = new Book(isbn, title, genre, year, new ArrayList<Author>(),bookStoreRepository.findById(idStore).orElseThrow());
        this.bookRepository.findAll().add(newBook);
    }

}
