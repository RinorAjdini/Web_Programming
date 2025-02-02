package mk.ukim.finki.wp.september2021.repository;

import mk.ukim.finki.wp.september2021.model.News;
import mk.ukim.finki.wp.september2021.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long>
{
    List<News> findAllByPriceLessThanAndTypeLike(Double price, NewsType type);
    List<News> findAllByPriceLessThan(Double price);
    List<News> findAllByTypeLike(NewsType newsType);
}
