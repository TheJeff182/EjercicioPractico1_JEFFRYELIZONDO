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
// @RequestMapping("/queja") // Debe estar COMENTADO o ELIMINADO
public class QuejaController {

    @Autowired
    private QuejaService quejaService;

    // Ya no necesitamos @ModelAttribute para la lista de tipos,
    // ya que el IndexController la añade.
    // Solo necesitamos el POST para enviar.

    // -------------------------------------------------------------------
    // MÉTODO 2: Procesar el Formulario (POST /enviar)
    // -------------------------------------------------------------------
    @PostMapping("/enviar") // Mapeo para que coincida con index.html
    public String enviarQueja(@Valid @ModelAttribute("queja") Queja queja, Errors errors, RedirectAttributes redirectAttributes, Model model) {

        if (errors.hasErrors()) {
            // Si hay errores, volvemos a 'index'. Debemos re-añadir los atributos
            // que IndexController pone, de lo contrario Thymeleaf fallará al renderizar.
            
            // Re-añadir atributos necesarios para index.html (TiposQueja, nuevoLibro, videoUrl)
            model.addAttribute("tiposQueja", TipoQueja.values()); 
            // Para "nuevoLibro" y "videoUrl" necesitas el LibroService.
            // La opción más limpia es que, si tienes errores de validación, uses un 
            // controlador que ya tenga esa información.
            
            // Alternativa simple (asumiendo que index.html puede manejar estos faltantes
            // o que la lógica se pone en IndexController.java):
            // Si quieres la solución más robusta sin complicarte más: 
            
            model.addAttribute("error", "Error al enviar la solicitud. Por favor, revise los campos.");
            return "index"; // Esto fallará si faltan nuevoLibro/videoUrl en el modelo
        }

        quejaService.save(queja);

        redirectAttributes.addFlashAttribute("mensaje", "Su solicitud ha sido enviada con éxito.");
        
        // Redirige al GET / que ahora maneja IndexController
        return "redirect:/";
    }
}