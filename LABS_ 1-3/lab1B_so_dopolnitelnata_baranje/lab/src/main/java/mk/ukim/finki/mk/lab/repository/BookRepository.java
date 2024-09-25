package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }
    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(b->b.getIsbn().equals(isbn)).findFirst().orElseThrow();
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
}
