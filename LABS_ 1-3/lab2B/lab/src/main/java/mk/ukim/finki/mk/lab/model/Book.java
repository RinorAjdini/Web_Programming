package mk.ukim.finki.mk.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    private Long id;
    public String isbn;
    public String title;
    public String genre;
    public int year ;
    public List<Author> authors;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year, List<Author> authors,BookStore bookStore) {
        this.id = (long)(Math.random()*1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>(authors);
        this.bookStore=bookStore;
    }
    public Book(String isbn, String title, String genre, int year,BookStore bookStore){
        this.id = (long)(Math.random()*1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.bookStore=bookStore;
    }
}
