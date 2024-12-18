package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.RutaPendienteService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/rutasPendientes")
public class AgregarRutaPendienteController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RutaPendienteService rutaPendienteService;

    @PostMapping("/agregar")
    public String agregarRutaPendiente(@RequestParam Long rutaId, Principal principal) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        rutaPendienteService.agregarRutaPendiente(usuario, rutaId);

        return "redirect:/perfil/" + usuario.getId() + "?tab=pendientes"; // Redirige al perfil en la pestaña de rutas pendientes
    }
}
