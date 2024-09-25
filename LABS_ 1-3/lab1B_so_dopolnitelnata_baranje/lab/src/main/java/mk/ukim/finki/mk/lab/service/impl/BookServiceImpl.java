package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.repository.AuthorRepository;
import mk.ukim.finki.mk.lab.repository.BookRepository;
import mk.ukim.finki.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
}
