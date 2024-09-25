package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Author;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    public List<Author> findAll(){
        return DataHolder.authors;
    }
    public Optional<Author> findById(Long id){
        return DataHolder.authors.stream().filter(a->a.getId().equals(id)).findFirst();
    }
}
