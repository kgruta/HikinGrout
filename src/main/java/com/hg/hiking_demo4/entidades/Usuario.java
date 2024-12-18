package com.hg.hiking_demo4.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
public class Usuario {
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Email(message = "El correo debe ser válido")
    @NotBlank(message = "El correo no puede estar vacío")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String contrasena;

    private int fechaNacimiento;

    @OneToMany(mappedBy = "usuario")
    private List<RutaRealizada> rutasRealizadas;

    @ManyToMany
    @JoinTable(
            name = "usuario_ruta_pendiente",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "ruta_id")
    )
    private List<RutaSenderismo> rutasPendientes;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public int getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(int fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public List<RutaRealizada> getRutasRealizadas() { return rutasRealizadas; }
    public void setRutasRealizadas(List<RutaRealizada> rutasRealizadas) { this.rutasRealizadas = rutasRealizadas; }

    public List<RutaSenderismo> getRutasPendientes() { return rutasPendientes; }
    public void setRutasPendientes(List<RutaSenderismo> rutasPendientes) { this.rutasPendientes = rutasPendientes; }

    public Object getContraseña() {
        return this.contrasena;
    }
}
