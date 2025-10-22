package Biblioteca.controllers;

import Biblioteca.domain.Queja;
import Biblioteca.domain.TipoQueja;
import Biblioteca.service.QuejaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuejaController {

    @Autowired
    private QuejaService quejaService;

  
    @PostMapping("/enviar") 
    public String enviarQueja(@Valid @ModelAttribute("queja") Queja queja, Errors errors, RedirectAttributes redirectAttributes, Model model) {

        if (errors.hasErrors()) {
           
            model.addAttribute("tiposQueja", TipoQueja.values()); 
           
            
            model.addAttribute("error", "Error al enviar la solicitud. Por favor, revise los campos.");
            return "index"; 
        }

        quejaService.save(queja);

        redirectAttributes.addFlashAttribute("mensaje", "Su solicitud ha sido enviada con éxito.");
        
        
        return "redirect:/";
    }
}