package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.PerfilService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@Controller
public class PerfilUsuarioControllerEdit {

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar el formulario de configuración de perfil para editar
    @GetMapping("/configuracion")
    public String mostrarFormularioConfiguracion(Principal principal, Model model) {
        // Obtener usuario actual basado en el email del principal
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        model.addAttribute("usuario", usuarioActual);  // Enviar usuario a la vista
        return "configuracion"; // Vista para editar el perfil
    }

    // Procesar la actualización del perfil
    @PostMapping("/configuracion")
    public String actualizarPerfil(@ModelAttribute Usuario usuario, Principal principal, Model model) {
        // Actualizar el perfil con los datos modificados
        Usuario usuarioActualizado = perfilService.actualizarPerfil(usuario);

        // Agregar mensaje de éxito
        model.addAttribute("mensaje", "Tu perfil ha sido actualizado correctamente.");
        return "configuracion"; // O redirigir a una página de perfil o éxito
    }
}
