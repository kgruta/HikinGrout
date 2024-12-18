package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.servicio.RutaSenderismoService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/rutas")
public class RutaSenderismoController {

    @Autowired
    private RutaSenderismoService rutaSenderismoService;

    @Autowired
    private UsuarioService usuarioService;

    // Mostrar todas las rutas en el formulario
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("rutas", rutaSenderismoService.obtenerRutasFiltradas(null, null, null));
        return "formularioRutas";  // Este es el formulario de rutas, donde los usuarios pueden filtrar
    }

    // Filtrar rutas según los parámetros proporcionados
    @GetMapping("/filtrar")
    public String filtrarRutas(@RequestParam(required = false) String comunidad,
                               @RequestParam(required = false) String dificultad,
                               @RequestParam(required = false) Integer duracion,
                               Model model) {

        // Obtén las rutas filtradas
        List<RutaSenderismo> rutasFiltradas = rutaSenderismoService.obtenerRutasFiltradas(comunidad, dificultad, duracion);

        // Si no hay rutas filtradas, mostrar un mensaje
        if (rutasFiltradas.isEmpty()) {
            model.addAttribute("mensaje", "No hay datos disponibles para la ruta solicitada. Por favor, intente con otros filtros.");
        } else {
            model.addAttribute("rutas", rutasFiltradas);
        }

        return "listaRutas";  // Vista que muestra las rutas filtradas
    }
}
