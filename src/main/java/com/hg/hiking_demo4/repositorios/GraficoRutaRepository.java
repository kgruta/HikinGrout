package com.hg.hiking_demo4.repositorios;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraficoRutaRepository extends JpaRepository<RutaSenderismo, Long> {
}
