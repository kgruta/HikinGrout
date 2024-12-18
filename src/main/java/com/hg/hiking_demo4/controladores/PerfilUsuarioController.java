package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.RutaRealizada;
import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.RutaRealizadaService;
import com.hg.hiking_demo4.servicio.RutaSenderismoService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/perfil")
public class PerfilUsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RutaSenderismoService rutaSenderismoService;

    @Autowired
    private RutaRealizadaService rutaRealizadaService;

    // Ver el perfil del usuario con las pestañas de rutas realizadas y pendientes
    @GetMapping("/{usuarioId}")
    public String verPerfil(@PathVariable Long usuarioId, @RequestParam(required = false, defaultValue = "realizadas") String tab, Principal principal, Model model) {
        Usuario perfilUsuario = usuarioService.obtenerUsuarioPorId(usuarioId);
        model.addAttribute("perfilUsuario", perfilUsuario);

        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        model.addAttribute("usuarioActual", usuarioActual);

        if (tab.equals("realizadas")) {
            model.addAttribute("activeTab", "realizadas");
            model.addAttribute("rutasRealizadas", perfilUsuario.getRutasRealizadas());
        } else if (tab.equals("pendientes")) {
            model.addAttribute("activeTab", "pendientes");
            model.addAttribute("rutasPendientes", perfilUsuario.getRutasPendientes());
        }

        return "perfilUsuario";
    }

    // Mostrar el formulario de valoración de la ruta pendiente
    @GetMapping("/pendientes/{rutaId}/valorar")
    public String formularioValorarRuta(@PathVariable Long rutaId, Principal principal, Model model) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        RutaSenderismo ruta = rutaSenderismoService.obtenerRutaPorId(rutaId);

        model.addAttribute("usuarioActual", usuarioActual);
        model.addAttribute("ruta", ruta);

        return "formularioValoracion";  // Este es el formulario para valorar la ruta pendiente
    }

    // Marcar ruta como realizada después de la valoración
    @PostMapping("/pendientes/{rutaId}/realizar")
    public String marcarRutaComoRealizada(@PathVariable Long rutaId, @RequestParam String comentarios, @RequestParam int puntuacion, Principal principal) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        RutaSenderismo ruta = rutaSenderismoService.obtenerRutaPorId(rutaId);

        // Crear la ruta realizada
        RutaRealizada rutaRealizada = new RutaRealizada();
        rutaRealizada.setUsuario(usuarioActual);
        rutaRealizada.setRuta(ruta);
        rutaRealizada.setFechaRealizacion(LocalDateTime.now());
        rutaRealizada.setComentarios(comentarios);
        rutaRealizada.setPuntuacion(puntuacion);
        rutaRealizadaService.guardarRutaRealizada(rutaRealizada);

        // Eliminar de pendientes
        usuarioActual.getRutasPendientes().remove(ruta);
        usuarioService.guardarUsuario(usuarioActual);

        // Redirigir a la pestaña de rutas realizadas en el perfil
        return "redirect:/perfil/" + usuarioActual.getId() + "?tab=realizadas";
    }

    // Guardar ruta como pendiente
    @PostMapping("/agregarRutaPendiente")
    public String agregarRutaPendiente(@RequestParam Long rutaId, Principal principal) {
        Usuario usuarioActual = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        RutaSenderismo ruta = rutaSenderismoService.obtenerRutaPorId(rutaId);

        // Añadir la ruta a las pendientes del usuario
        usuarioActual.getRutasPendientes().add(ruta);
        usuarioService.guardarUsuario(usuarioActual);

        // Redirigir al perfil del usuario
        return "redirect:/perfil/" + usuarioActual.getId() + "?tab=pendientes";
    }
}
