package mk.ukim.finki.wp.june2021.repository;

import mk.ukim.finki.wp.june2021.model.Match;
import mk.ukim.finki.wp.june2021.model.MatchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {
    List<Match> findByPriceLessThanAndType(Double price, MatchType type);
    List<Match> findByPriceLessThan(Double price);
    List<Match> findByType(MatchType type);
}
