package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors;
    public static List<Book> books;
    @PostConstruct
    public void init(){
        authors = new ArrayList<>();
        authors.add(new Author(1L,"William ","Shakespeare","William Shakespeare (April 1564 – 23 April 1616) was an English playwright, poet and actor."));
        authors.add(new Author(2L,"Fyodor","Dostoevsky","Fyodor Dostoevsky was a Russian novelist, short story writer, essayist and journalist."));
        authors.add(new Author(3L,"Leo","Tolstoy","Leo Nikolayevich Tolstoy was a Russian writer regarded as one of the greatest authors of all time."));
        authors.add(new Author(4L,"Charles","Dickens","Charles Dickens was an English novelist and social critic who created some of the world's best-known fictional characters, and is regarded by many as the greatest novelist of the Victorian era."));
        authors.add(new Author(5L,"Victor","Hugo","Victor Hugo was a French romantic writer and politician. During a literary career that spanned more than sixty years, he wrote in a variety of genres and forms."));

        books = new ArrayList<>();
        books.add(new Book("978-0-575-04139-4","Good Omens","Action Fantasy Comedy Drama Adventure Mystery",1990,
                Arrays.asList(authors.get(0))));
        books.add(new Book("978-1-59474-334-8","The Eye of the World","Mystery Crime Fantasy Fiction",1990,
                Arrays.asList(authors.get(1))));
        books.add(new Book("978-0-8041-3902-1","In Search of Lost Time","Philosophical Modern Social Literature",2011,
                Arrays.asList(authors.get(2))));
        books.add(new Book("978-0-19-953571-6","To Kill a Mockingbird","Novel Thriller Fiction Story",1848,
                Arrays.asList(authors.get(3))));
        books.add(new Book("978-0-271-81109-7","The Lion, the Witch and the Wardrobe","Fantasy Fiction Literature Novel",1988,
                Arrays.asList(authors.get(3))));

    }

}
