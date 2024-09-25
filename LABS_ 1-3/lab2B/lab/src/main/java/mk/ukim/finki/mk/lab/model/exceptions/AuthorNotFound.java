package mk.ukim.finki.mk.lab.model.exceptions;

public class AuthorNotFound extends RuntimeException{
    public AuthorNotFound(Long id) {
        super(String.format("Author with id %d does not exist",id));
    }
}
