package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.RutaRealizadaService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/rutaRealizada")
public class RutaRealizadaController {

    @Autowired
    private RutaRealizadaService rutaRealizadaService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/{id}/comentar")
    public String comentarRutaRealizada(@PathVariable Long id,
                                        @RequestParam("comentarios") String comentarios,
                                        @RequestParam("puntuacion") double puntuacion,
                                        Principal principal) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        rutaRealizadaService.actualizarComentariosYPuntuacion(id, usuarioActual, comentarios, puntuacion);
        return "redirect:/perfil/" + usuarioActual.getId();
    }
}
