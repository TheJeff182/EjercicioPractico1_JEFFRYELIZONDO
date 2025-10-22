package Biblioteca.controllers;

import Biblioteca.domain.Queja;
import Biblioteca.domain.TipoQueja;
import Biblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public String index(Model model) {
        
        // 1. Añadir el objeto queja 
        if (!model.containsAttribute("queja")) {
             model.addAttribute("queja", new Queja());
        }
        
        // 2. Añadir el objeto nuevoLibro
        model.addAttribute("nuevoLibro", libroService.getNuevoLibro());
        
        // 3. CORRECCIÓN: Pasar los tipos de queja usando el nombre 'tiposQueja'
        // para que coincida con lo que el HTML espera.
        model.addAttribute("tiposQueja", TipoQueja.values()); // <--- ¡CAMBIADO DE "tipos" A "tiposQueja"!
        
        // 4. Pasa la URL para el video incrustado
        model.addAttribute("videoUrl", "https://youtu.be/C0DPdy98e4c?si=vWuWzlVPI7Wy0onU"); 

        return "index";
    }
}