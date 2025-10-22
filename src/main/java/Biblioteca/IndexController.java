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
        
      
        if (!model.containsAttribute("queja")) {
             model.addAttribute("queja", new Queja());
        }
        
       
        model.addAttribute("nuevoLibro", libroService.getNuevoLibro());
        
    
        model.addAttribute("tiposQueja", TipoQueja.values()); 
        
    
        model.addAttribute("videoUrl", "https://youtu.be/C0DPdy98e4c?si=vWuWzlVPI7Wy0onU"); 

        return "index";
    }
}