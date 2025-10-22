package Biblioteca.service;

import Biblioteca.domain.Libro;
import Biblioteca.repository.LibroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Transactional(readOnly = true)
    public List<Libro> getLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Libro> getLibrosPorCategoria(Long categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId);
    }

    @Transactional(readOnly = true)
    public Libro getLibro(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Libro libro) {
        libroRepository.save(libro);
    }

    @Transactional
    public void delete(Long id) {
        libroRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Libro getNuevoLibro() {
        return libroRepository.findTopByOrderByCreatedAtDesc();
    }
}