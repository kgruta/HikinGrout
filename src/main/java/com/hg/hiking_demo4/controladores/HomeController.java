package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        // Obtener el usuario actualmente autenticado utilizando el email (nombre de usuario)
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());

        // Verificar que el usuario existe
        if (usuarioActual == null) {
            // Manejar el caso donde el usuario no se encuentra (opcional)
            return "redirect:/login?error=usuarioNoEncontrado";
        }

        // Añadir el objeto usuarioActual al modelo
        model.addAttribute("usuarioActual", usuarioActual);

        return "home"; // Nombre de la plantilla Thymeleaf sin la extensión '.html'
    }
}
