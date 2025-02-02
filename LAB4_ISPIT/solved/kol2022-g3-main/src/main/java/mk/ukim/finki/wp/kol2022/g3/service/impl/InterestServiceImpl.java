package mk.ukim.finki.wp.kol2022.g3.service.impl;

import mk.ukim.finki.wp.kol2022.g3.model.Interest;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidInterestIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.InterestRepository;
import mk.ukim.finki.wp.kol2022.g3.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService
{
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public Interest findById(Long id) {
        return interestRepository.findById(id).orElseThrow(InvalidInterestIdException::new);
    }

    @Override
    public List<Interest> listAll() {
        return interestRepository.findAll();
    }

    @Override
    public Interest create(String name) {
        Interest interest = new Interest(name);
        return interestRepository.save(interest);
    }
}
