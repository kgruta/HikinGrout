package com.hg.hiking_demo4.servicio;
import com.hg.hiking_demo4.entidades.RutaSenderismo;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.RutaSenderismoRepository;
import com.hg.hiking_demo4.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaPendienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RutaSenderismoRepository rutaSenderismoRepository;

    public void agregarRutaPendiente(Usuario usuario, Long rutaId) {
        RutaSenderismo ruta = rutaSenderismoRepository.findById(rutaId).orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
        List<RutaSenderismo> pendientes = usuario.getRutasPendientes();
        if (!pendientes.contains(ruta)) {
            pendientes.add(ruta);
            usuarioRepository.save(usuario);
        }
    }

    public void eliminarRutaPendiente(Usuario usuario, Long rutaId) {
        RutaSenderismo ruta = rutaSenderismoRepository.findById(rutaId).orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
        List<RutaSenderismo> pendientes = usuario.getRutasPendientes();
        if (pendientes.contains(ruta)) {
            pendientes.remove(ruta);
            usuarioRepository.save(usuario);
        }
    }
}
