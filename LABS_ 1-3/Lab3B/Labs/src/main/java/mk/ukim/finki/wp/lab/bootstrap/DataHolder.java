package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors;
    public static List<Book> books;
    public static List<BookStore> bookStoreList;
//    @PostConstruct
//    public void init(){
//        authors = new ArrayList<>();
//        authors.add(new Author("William ","Shakespeare","William Shakespeare (April 1564 – 23 April 1616) was an English playwright, poet and actor."));
//        authors.add(new Author("Fyodor","Dostoevsky","Fyodor Dostoevsky was a Russian novelist, short story writer, essayist and journalist."));
//        authors.add(new Author("Leo","Tolstoy","Leo Nikolayevich Tolstoy was a Russian writer regarded as one of the greatest authors of all time."));
//        authors.add(new Author("Charles","Dickens","Charles Dickens was an English novelist and social critic who created some of the world's best-known fictional characters, and is regarded by many as the greatest novelist of the Victorian era."));
//        authors.add(new Author("Victor","Hugo","Victor Hugo was a French romantic writer and politician. During a literary career that spanned more than sixty years, he wrote in a variety of genres and forms."));
//
//        bookStoreList = new ArrayList<>();
//        bookStoreList.add(new BookStore("Strand Bookstore","New York, USA","828 Broadway"));
//        bookStoreList.add(new BookStore("Daunt Books","London, UK","102 Burnside St"));
//        bookStoreList.add(new BookStore("Shakespeare","Paris, France"," 37 Rue de la Bucherie"));
//        bookStoreList.add(new BookStore("Kinokuniya","Tokyo, Japan","3 Chome-14-4 Shinjuku"));
//        bookStoreList.add(new BookStore("Libreria Acqua Alta","Venice, Italy","Santa Maria 5176/B"));
//
//        books = new ArrayList<>();
//        books.add(new Book("978-0-575-04139-4","Good Omens","Action Fantasy Comedy Drama Adventure Mystery",1990,
//                Arrays.asList(authors.get(0)),bookStoreList.get(0)));
//        books.add(new Book("978-1-59474-334-8","The Eye of the World","Mystery Crime Fantasy Fiction",1990,
//                Arrays.asList(authors.get(1)),bookStoreList.get(1)));
//        books.add(new Book("978-0-8041-3902-1","In Search of Lost Time","Philosophical Modern Social Literature",2011,
//                Arrays.asList(authors.get(2)),bookStoreList.get(2)));
//        books.add(new Book("978-0-19-953571-6","To Kill a Mockingbird","Novel Thriller Fiction Story",1848,
//                Arrays.asList(authors.get(3)),bookStoreList.get(3)));
//        books.add(new Book("978-0-271-81109-7","The Lion, the Witch and the Wardrobe","Fantasy Fiction Literature Novel",1988,
//                Arrays.asList(authors.get(3)),bookStoreList.get(1)));
//
//    }

}
