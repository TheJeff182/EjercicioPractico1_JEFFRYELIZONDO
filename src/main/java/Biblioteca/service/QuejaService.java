package Biblioteca.service;

import Biblioteca.domain.Queja;
import Biblioteca.repository.QuejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuejaService {

    @Autowired
    private QuejaRepository quejaRepository;

    @Transactional
    public void save(Queja queja) {
        quejaRepository.save(queja);
    }
}