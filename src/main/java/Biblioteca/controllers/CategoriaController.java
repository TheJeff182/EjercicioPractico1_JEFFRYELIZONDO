// Archivo: Biblioteca/controller/CategoriaController.java

package Biblioteca.controllers;

import Biblioteca.service.CategoriaService; // Asume que tienes este servicio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categoria") // Mapea todas las rutas bajo /categoria
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService; // Inyecta el servicio que creaste

    @GetMapping("/listado") // Mapea la ruta /categoria/listado
    public String listarCategorias(Model model) {
        
        // 1. Obtiene la lista de categorías
        var categorias = categoriaService.getCategorias(); 
        
        // 2. Agrega la lista al modelo para que Thymeleaf la use
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size()); 
        
        // 3. Retorna la vista: /templates/categoria/listado.html
        return "categoria/listado"; 
    }
}