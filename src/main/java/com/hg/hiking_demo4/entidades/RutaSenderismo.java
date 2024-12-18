package com.hg.hiking_demo4.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class RutaSenderismo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la ruta no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La ubicación de la ruta no puede estar vacía")
    private String ubicacion;

    private String comunidad;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    @Min(value = 1, message = "La duración debe ser al menos 1 hora")
    private int duracion; // Duración en minutos

    @Column(length = 1000)
    private String descripcion;

    private double latitud;
    private double longitud;

    @Column(name = "ubicacion_url")
    private String ubicacionUrl;


    @ManyToMany(mappedBy = "rutasPendientes")
    private List<Usuario> usuariosPendientes;

    @OneToMany(mappedBy = "ruta")
    private List<RutaRealizada> rutasRealizadas;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaModificacion;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int altitudMaxima;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int altitudMinima;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int desnivelPositivo;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int desnivelNegativo;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private double distanciaKm;


    @Enumerated(EnumType.STRING)
    private TipoRuta tipoRuta;

    @Column(length = 500)
    private String senalizacion;

    @Column(length = 500)
    private String epocaRecomendada;

    @Column(length = 500)
    private String tipoTerreno;

    @Column(length = 1000)
    private String puntosInteres;

    @Column(length = 500)
    private String accesibilidad;

    @Column(length = 500)
    private String serviciosCercanos;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    private float puntuacionMedia;

    @Column(length = 1000)
    private String consejos;

    public enum Dificultad {
        FACIL,
        MODERADA,
        DIFICIL
    }

    public enum TipoRuta {
        CIRCULAR,
        LINEAL,
        IDA_Y_VUELTA
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getUbicacionUrl() {
        return ubicacionUrl;
    }

    public void setUbicacionUrl(String ubicacionUrl) {
        this.ubicacionUrl = ubicacionUrl;
    }

    public List<RutaRealizada> getRutasRealizadas() {
        return rutasRealizadas;
    }

    public void setRutasRealizadas(List<RutaRealizada> rutasRealizadas) {
        this.rutasRealizadas = rutasRealizadas;
    }

    public List<Usuario> getUsuariosPendientes() {
        return usuariosPendientes;
    }

    public void setUsuariosPendientes(List<Usuario> usuariosPendientes) {
        this.usuariosPendientes = usuariosPendientes;
    }



    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getDesnivelPositivo() {
        return desnivelPositivo;
    }

    public void setDesnivelPositivo(int desnivelPositivo) {
        this.desnivelPositivo = desnivelPositivo;
    }

    public int getDesnivelNegativo() {
        return desnivelNegativo;
    }

    public void setDesnivelNegativo(int desnivelNegativo) {
        this.desnivelNegativo = desnivelNegativo;
    }

    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public int getAltitudMinima() {
        return altitudMinima;
    }

    public void setAltitudMinima(int altitudMinima) {
        this.altitudMinima = altitudMinima;
    }

    public TipoRuta getTipoRuta() {
        return tipoRuta;
    }

    public void setTipoRuta(TipoRuta tipoRuta) {
        this.tipoRuta = tipoRuta;
    }

    public String getSenalizacion() {
        return senalizacion;
    }

    public void setSenalizacion(String senalizacion) {
        this.senalizacion = senalizacion;
    }

    public String getEpocaRecomendada() {
        return epocaRecomendada;
    }

    public void setEpocaRecomendada(String epocaRecomendada) {
        this.epocaRecomendada = epocaRecomendada;
    }

    public String getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public String getPuntosInteres() {
        return puntosInteres;
    }

    public void setPuntosInteres(String puntosInteres) {
        this.puntosInteres = puntosInteres;
    }

    public String getAccesibilidad() {
        return accesibilidad;
    }

    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

    public String getServiciosCercanos() {
        return serviciosCercanos;
    }

    public void setServiciosCercanos(String serviciosCercanos) {
        this.serviciosCercanos = serviciosCercanos;
    }

    public double getPuntuacionMedia() {
        return puntuacionMedia;
    }

    public void setPuntuacionMedia(float puntuacionMedia) {
        this.puntuacionMedia = puntuacionMedia;
    }

    public String getConsejos() {
        return consejos;
    }

    public void setConsejos(String consejos) {
        this.consejos = consejos;
    }
}
