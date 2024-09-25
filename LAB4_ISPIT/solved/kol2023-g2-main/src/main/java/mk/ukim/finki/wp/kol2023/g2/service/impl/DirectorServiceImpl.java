package mk.ukim.finki.wp.kol2023.g2.service.impl;

import mk.ukim.finki.wp.kol2023.g2.model.Director;
import mk.ukim.finki.wp.kol2023.g2.model.exceptions.InvalidDirectorIdException;
import mk.ukim.finki.wp.kol2023.g2.repository.DirectorRepository;
import mk.ukim.finki.wp.kol2023.g2.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Director findById(Long id) {
        return directorRepository.findById(id).orElseThrow(InvalidDirectorIdException::new);
    }

    @Override
    public List<Director> listAll() {
        return directorRepository.findAll();
    }

    @Override
    public Director create(String name) {
        Director d1 = new Director(name);
        return directorRepository.save(d1);
    }
}
