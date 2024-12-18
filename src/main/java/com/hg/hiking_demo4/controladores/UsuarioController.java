package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login"; // Vista del formulario de login
    }

    @PostMapping("/login")
    public String autenticarUsuario(@RequestParam String email,
                                    @RequestParam String contraseña,
                                    Model model) {
        Optional<Usuario> usuario = usuarioService.autenticarUsuario(email, contraseña);

        if (usuario.isPresent()) {
            // Si las credenciales son correctas, redirigir al home
            return "redirect:/home"; // O a la vista correspondiente del usuario autenticado
        } else {
            // Si las credenciales no son correctas, mostrar un error y permanecer en la página de login
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
}
