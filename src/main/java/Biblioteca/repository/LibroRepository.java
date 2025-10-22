/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Biblioteca.repository;

import Biblioteca.domain.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    
    // Metodo para encontrar libros por ID de categoría 
    List<Libro> findByCategoriaId(Long categoriaId);
    
    // Mtodo para obtener el último libro
    Libro findTopByOrderByCreatedAtDesc();
    
    
}