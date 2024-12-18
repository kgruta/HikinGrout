package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.repositorios.GraficoRutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraficoRutaServicio {

    @Autowired
    private GraficoRutaRepository graficoRutaRepository;

    // Método para obtener una ruta por ID
    public Optional<RutaSenderismo> obtenerRutaPorId(Long id) {
        return graficoRutaRepository.findById(id);
    }

    // Método para listar todas las rutas
    public List<RutaSenderismo> obtenerTodasLasRutas() {
        return graficoRutaRepository.findAll();
    }
}
