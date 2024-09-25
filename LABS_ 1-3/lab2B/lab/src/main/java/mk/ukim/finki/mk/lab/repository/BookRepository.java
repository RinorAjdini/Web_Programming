package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }
    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(b->b.getIsbn().equals(isbn)).findFirst().orElseThrow();
    }
    public Optional<Book> findById(Long id){
        return DataHolder.books.stream().filter(b->b.getId().equals(id)).findFirst();
    }
    public Author addAuthorToBook(Author author, Book book){
        Book wantedBook = DataHolder.books.stream()
                .filter(b->b.equals(book)).findFirst().orElse(null);
        if(wantedBook!=null) {
            wantedBook.getAuthors().removeIf(au->au.equals(author));
            wantedBook.getAuthors().add(author);
            }
            return author;
    }
    public void deleteById(Long id){
        DataHolder.books.removeIf(b->b.getId().equals(id));
    }
    public Optional<Book> save(String isbn, String title, String genre, int year, List<Author> authors, BookStore bookStore)
    {
        if(authors==null || bookStore==null)
            throw new IllegalArgumentException();
        Book book = new Book(isbn,title,genre,year,authors,bookStore);
        DataHolder.books.removeIf(b->b.getIsbn().equals(book.getIsbn()));
        DataHolder.books.add(book);
        return Optional.of(book);
    }
    public Optional<Book> save(String isbn, String title, String genre, int year, BookStore bookStore)
    {
        if( bookStore==null)
            throw new IllegalArgumentException();
        Book book = new Book(isbn,title,genre,year,bookStore);
        DataHolder.books.removeIf(b->b.getIsbn().equals(book.getIsbn()));
        DataHolder.books.add(book);
        return Optional.of(book);
    }

}
