package com.hg.hiking_demo4.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class RutaRealizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ruta_id", nullable = false)
    private RutaSenderismo ruta;

    @NotNull
    private LocalDateTime fechaRealizacion;

    @Column(length = 1000)
    private String comentarios;

    private double puntuacion;

    // Relación con fotos subidas por el usuario durante la ruta
    @ElementCollection
    private List<String> fotosSubidas;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RutaSenderismo getRuta() {
        return ruta;
    }

    public void setRuta(RutaSenderismo ruta) {
        this.ruta = ruta;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public List<String> getFotosSubidas() {
        return fotosSubidas;
    }

    public void setFotosSubidas(List<String> fotosSubidas) {
        this.fotosSubidas = fotosSubidas;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
