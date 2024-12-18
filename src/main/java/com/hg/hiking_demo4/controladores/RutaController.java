package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.RutaRealizada;
import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.GraficoRutaServicio;
import com.hg.hiking_demo4.servicio.RutaSenderismoService;
import com.hg.hiking_demo4.servicio.RutaRealizadaService;
import com.hg.hiking_demo4.servicio.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/rutas")
public class RutaController {

    @Autowired
    private RutaSenderismoService rutaSenderismoService;

    @Autowired
    private RutaRealizadaService rutaRealizadaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GraficoRutaServicio graficoRutaServicio;

    // Mostrar Rutas Pendientes
    @GetMapping("/pendientes")
    public String verRutasPendientes(Principal principal, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        model.addAttribute("rutasPendientes", usuario.getRutasPendientes());
        return "rutasPendientes"; // Plantilla para rutas pendientes
    }

    // Mostrar detalles de la ruta seleccionada
    @GetMapping("/detalles/{rutaId}")
    public String verDetallesRuta(@PathVariable Long rutaId, Model model) {
        RutaSenderismo ruta = rutaSenderismoService.obtenerRutaPorId(rutaId);

        if (ruta != null) {
            model.addAttribute("ruta", ruta);
            return "detalleRuta"; // Página donde se muestra la información completa de la ruta
        } else {
            model.addAttribute("error", "Ruta no encontrada");
            return "error"; // En caso de que la ruta no se encuentre
        }
    }

    // Mover Ruta de Pendiente a Realizada
    @PostMapping("/pendientes/{rutaId}/realizar")
    public String marcarComoRealizada(@PathVariable Long rutaId, Principal principal) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(principal.getName());
        RutaSenderismo ruta = rutaSenderismoService.obtenerRutaPorId(rutaId);

        // Crear RutaRealizada y guardar
        RutaRealizada rutaRealizada = new RutaRealizada();
        rutaRealizada.setUsuario(usuario);
        rutaRealizada.setRuta(ruta);
        rutaRealizada.setFechaRealizacion(LocalDateTime.now());

        rutaRealizadaService.guardarRutaRealizada(rutaRealizada);

        // Eliminar la ruta de pendientes
        usuario.getRutasPendientes().remove(ruta);
        usuarioService.guardarUsuario(usuario);

        return "redirect:/rutas/pendientes";
    }

    // Página para añadir valoración y comentarios
    @GetMapping("/realizadas/{rutaId}/valorar")
    public String valorarRuta(@PathVariable Long rutaId, Model model) {
        RutaRealizada rutaRealizada = rutaRealizadaService.obtenerPorId(rutaId);
        model.addAttribute("rutaRealizada", rutaRealizada);
        return "valorarRuta"; // Plantilla para valorar la ruta
    }

    // Guardar valoración y comentarios
    @PostMapping("/realizadas/{rutaId}/valorar")
    public String guardarValoracion(@PathVariable Long rutaId,
                                    @RequestParam double puntuacion,
                                    @RequestParam String comentarios) {
        RutaRealizada rutaRealizada = rutaRealizadaService.obtenerPorId(rutaId);
        rutaRealizada.setPuntuacion(puntuacion);
        rutaRealizada.setComentarios(comentarios);

        rutaRealizadaService.guardarRutaRealizada(rutaRealizada);
        return "redirect:/perfil"; // Redirige al perfil después de guardar
    }
}
