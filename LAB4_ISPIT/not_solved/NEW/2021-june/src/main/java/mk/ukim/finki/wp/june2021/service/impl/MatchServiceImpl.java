package mk.ukim.finki.wp.june2021.service.impl;

import mk.ukim.finki.wp.june2021.model.Match;
import mk.ukim.finki.wp.june2021.model.MatchType;
import mk.ukim.finki.wp.june2021.model.exceptions.InvalidMatchIdException;
import mk.ukim.finki.wp.june2021.repository.MatchRepository;
import mk.ukim.finki.wp.june2021.service.MatchLocationService;
import mk.ukim.finki.wp.june2021.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchLocationService matchLocationService;
    public MatchServiceImpl(MatchRepository matchRepository, MatchLocationService matchLocationService) {
        this.matchRepository = matchRepository;
        this.matchLocationService = matchLocationService;
    }


    @Override
    public List<Match> listAllMatches() {
        return this.matchRepository.findAll();
    }

    @Override
    public Match findById(Long id) {
        return this.matchRepository.findById(id).orElseThrow(InvalidMatchIdException::new);
    }

    @Override
    public Match create(String name, String description, Double price, MatchType type, Long location) {
        return this.matchRepository.save(new Match(name,description,price,type,this.matchLocationService.findById(location)));
    }

    @Override
    public Match update(Long id, String name, String description, Double price, MatchType type, Long location) {
        Match match = findById(id);
        match.setName(name);
        match.setDescription(description);
        match.setPrice(price);
        match.setType(type);
        match.setLocation(this.matchLocationService.findById(location));
        return this.matchRepository.save(match);
    }

    @Override
    public Match delete(Long id) {
        Match match = findById(id);
        this.matchRepository.delete(match);
        return match;
    }

    @Override
    public Match follow(Long id) {
        Match match = findById(id);
        match.setFollows(match.getFollows()+1);
        return matchRepository.save(match);
    }

    @Override
    public List<Match> listMatchesWithPriceLessThanAndType(Double price, MatchType type) {
        if(price!=null && type!=null){
            return this.matchRepository.findByPriceLessThanAndType(price,type);
        }
        else if(price!=null){
            return this.matchRepository.findByPriceLessThan(price);
        }
        else if(type!=null){
            return this.matchRepository.findByType(type);
        }
        return this.matchRepository.findAll();
    }
}
