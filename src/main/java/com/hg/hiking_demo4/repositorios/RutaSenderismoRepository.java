package com.hg.hiking_demo4.repositorios;

import com.hg.hiking_demo4.entidades.RutaSenderismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface RutaSenderismoRepository extends JpaRepository<RutaSenderismo, Long> {

    @Query("SELECT r FROM RutaSenderismo r WHERE " +
            "(:comunidad IS NULL OR r.comunidad = :comunidad) AND " +
            "(:dificultad IS NULL OR r.dificultad = :dificultad) AND " +
            "(:duracion IS NULL OR r.duracion >= :duracion)")
    List<RutaSenderismo> findByFilters(@Param("comunidad") String comunidad,
                                       @Param("dificultad") RutaSenderismo.Dificultad dificultad,
                                       @Param("duracion") Integer duracion);



}
