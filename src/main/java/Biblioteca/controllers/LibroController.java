package Biblioteca.controllers;

import Biblioteca.domain.Libro;
import Biblioteca.service.CategoriaService;
import Biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("libros", libroService.getLibros());
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "libro/listado";
    }
    
    @GetMapping("/listadoPorCategoria")
    public String listadoPorCategoria(@RequestParam("categoriaId") Long categoriaId, Model model) {
        model.addAttribute("libros", libroService.getLibrosPorCategoria(categoriaId));
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "libro/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevoLibro(Libro libro, Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "libro/modifica"; 
    }

    @PostMapping("/guardar")
    public String guardarLibro(@Valid Libro libro, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("categorias", categoriaService.getCategorias());
            return "libro/modifica";
        }
        libroService.save(libro);
        return "redirect:/libro/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificarLibro(Libro libro, Model model) {
        libro = libroService.getLibro(libro.getId());
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("libro", libro);
        return "libro/modifica";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(Libro libro) {
        libroService.delete(libro.getId());
        return "redirect:/libro/listado";
    }
}