package mk.ukim.finki.mk.lab.model.exceptions;

public class BookStoreNotFound extends RuntimeException{
    public BookStoreNotFound(Long id) {
        super(String.format("BookStore with id %d does not exist",id));
    }
}
