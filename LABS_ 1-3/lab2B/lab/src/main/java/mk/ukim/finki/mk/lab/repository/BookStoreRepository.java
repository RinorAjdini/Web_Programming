package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookStoreRepository {
    public List<BookStore> findAll(){
        return DataHolder.bookStoreList;
    }
    public Optional<BookStore> findById(Long id){
       return  DataHolder.bookStoreList.stream().filter(bs->bs.getId().equals(id)).findFirst();
    }
}
