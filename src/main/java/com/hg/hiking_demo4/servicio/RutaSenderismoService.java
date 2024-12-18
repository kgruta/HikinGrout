package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.repositorios.RutaSenderismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaSenderismoService {

    @Autowired
    private RutaSenderismoRepository rutaSenderismoRepository;

    public List<RutaSenderismo> obtenerRutasFiltradas(String comunidad, String dificultad, Integer duracion) {
        // Normalización de filtros
        String comunidadFiltrada = (comunidad != null && !comunidad.isEmpty()) ? comunidad : null;
        Integer duracionFiltrada = (duracion != null && duracion > 0) ? duracion : null;
        RutaSenderismo.Dificultad dificultadFiltrada = null;

        if (dificultad != null && !dificultad.isEmpty()) {
            try {
                dificultadFiltrada = RutaSenderismo.Dificultad.valueOf(dificultad.toUpperCase());
            } catch (IllegalArgumentException e) {
                // Log y gestión del error si se necesita
                System.err.println("Dificultad no válida: " + dificultad);
            }
        }

        return rutaSenderismoRepository.findByFilters(comunidadFiltrada, dificultadFiltrada, duracionFiltrada);
    }
    // Método para obtener todas las rutas
    public List<RutaSenderismo> obtenerTodasLasRutas() {
        return rutaSenderismoRepository.findAll();
    }

    // Aquí podrías agregar métodos adicionales según sea necesario
    public RutaSenderismo obtenerRutaPorId(Long id) {
        return rutaSenderismoRepository.findById(id).orElse(null);
    }

    // Si tienes algún filtro, puedes agregar métodos como este:
    public List<RutaSenderismo> obtenerRutasPorComunidad(String comunidad) {
        // Puedes crear una consulta personalizada con Spring Data JPA si lo necesitas
        return rutaSenderismoRepository.findAll(); // Este es solo un ejemplo básico
    }

    public List<RutaSenderismo> filtrarRutas(String comunidad, RutaSenderismo.Dificultad dificultad, Integer duracion) {
        return rutaSenderismoRepository.findByFilters(comunidad, dificultad, duracion);
    }
}