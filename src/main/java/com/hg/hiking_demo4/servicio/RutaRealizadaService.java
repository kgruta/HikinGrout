package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.RutaRealizada;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.RutaRealizadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaRealizadaService {

    @Autowired
    private RutaRealizadaRepository rutaRealizadaRepository;

    public void actualizarComentariosYPuntuacion(Long rutaRealizadaId, Usuario usuario, String comentarios, double puntuacion) {
        RutaRealizada rr = rutaRealizadaRepository.findById(rutaRealizadaId)
                .orElseThrow(() -> new IllegalArgumentException("RutaRealizada no encontrada"));

        // Verificar que el usuario sea el propietario
        if (!rr.getUsuario().getId().equals(usuario.getId())) {
            throw new SecurityException("No tienes permisos para editar esta ruta realizada.");
        }

        rr.setComentarios(comentarios);
        rr.setPuntuacion(puntuacion);
        rutaRealizadaRepository.save(rr);
    }

    public void guardarRutaRealizada(RutaRealizada rutaRealizada) {
        rutaRealizadaRepository.save(rutaRealizada);
    }

    public RutaRealizada obtenerPorId(Long id) {
        return rutaRealizadaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
    }

    // Implementación del método que falta
    public List<RutaRealizada> obtenerRutasRealizadasPorUsuario(Usuario usuario) {
        return rutaRealizadaRepository.findByUsuario(usuario); // Asumiendo que tienes este método en tu repositorio
    }
}
