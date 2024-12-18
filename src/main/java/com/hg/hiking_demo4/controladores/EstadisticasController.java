package com.hg.hiking_demo4.controladores;

import org.springframework.ui.Model;  // IMPORTANTE: esta es la importación correcta
import com.hg.hiking_demo4.entidades.RutaRealizada;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.servicio.RutaRealizadaService;
import com.hg.hiking_demo4.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;
@Controller
public class EstadisticasController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RutaRealizadaService rutaRealizadaService;

    @GetMapping("/estadisticas")
    public String verEstadisticas(Principal principal, Model model) {
        // Obtener el usuario actual
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(principal.getName());

        // Obtener las rutas realizadas por el usuario
        List<RutaRealizada> rutasRealizadas = rutaRealizadaService.obtenerRutasRealizadasPorUsuario(usuario);

        // Calcular estadísticas
        int totalRutas = rutasRealizadas.size();
        double distanciaTotal = 0;
        int desnivelPositivoTotal = 0;
        int desnivelNegativoTotal = 0;
        double duracionTotal = 0;

        for (RutaRealizada ruta : rutasRealizadas) {
            distanciaTotal += ruta.getRuta().getDistanciaKm();
            desnivelPositivoTotal += ruta.getRuta().getDesnivelPositivo();
            desnivelNegativoTotal += ruta.getRuta().getDesnivelNegativo();
            duracionTotal += ruta.getRuta().getDuracion(); // Duración total de todas las rutas realizadas
        }

        // Agregar estadísticas al modelo
        model.addAttribute("totalRutas", totalRutas);
        model.addAttribute("distanciaTotal", distanciaTotal);
        model.addAttribute("desnivelPositivoTotal", desnivelPositivoTotal);
        model.addAttribute("desnivelNegativoTotal", desnivelNegativoTotal);
        model.addAttribute("duracionTotal", duracionTotal);

        return "estadisticas"; // Vista que mostrará las estadísticas
    }
}
