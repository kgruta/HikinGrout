package com.hg.hiking_demo4.controladores;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.servicio.GraficoRutaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class GraficoRutaController {

    @Autowired
    private GraficoRutaServicio graficoRutaServicio;

    // Mostrar formulario para seleccionar rutas
    @GetMapping("/formularioRutasGrafico")
    public String mostrarFormulario(Model model) {
        model.addAttribute("rutas", graficoRutaServicio.obtenerTodasLasRutas());
        return "formularioRutasGrafico"; // HTML para el formulario
    }

    // Mostrar gráfico para la ruta seleccionada
    @GetMapping("/graficoRuta")
    public String mostrarGrafico(@RequestParam("rutaId") Long rutaId, Model model) {
        Optional<RutaSenderismo> rutaOpt = graficoRutaServicio.obtenerRutaPorId(rutaId);

        if (rutaOpt.isPresent()) {
            RutaSenderismo ruta = rutaOpt.get();
            model.addAttribute("ruta", ruta);
            model.addAttribute("distancia", ruta.getDistanciaKm());
            model.addAttribute("desnivelPositivo", ruta.getDesnivelPositivo());
            model.addAttribute("desnivelNegativo", ruta.getDesnivelNegativo());
            model.addAttribute("altitudMaxima", ruta.getAltitudMaxima());
            model.addAttribute("altitudMinima", ruta.getAltitudMinima());
            return "graficoRuta"; // HTML para mostrar el gráfico
        } else {
            model.addAttribute("error", "Ruta no encontrada");
            return "error"; // Página de error
        }
    }
}
