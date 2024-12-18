package com.hg.hiking_demo4.repositorios;
import com.hg.hiking_demo4.entidades.RutaRealizada;
import com.hg.hiking_demo4.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRealizadaRepository extends JpaRepository<RutaRealizada, Long> {
    List<RutaRealizada> findByUsuario(Usuario usuario);
}
