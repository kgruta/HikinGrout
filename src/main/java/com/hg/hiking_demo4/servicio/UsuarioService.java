package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Instanciamos el encoder de BCrypt
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Guardar usuario con contraseña encriptada
    public void guardarUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardarla
        String hashedPassword = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(hashedPassword);  // Asignamos la contraseña encriptada
        usuarioRepository.save(usuario);
    }

    // Autenticar usuario comparando las contraseñas encriptadas
    public Optional<Usuario> autenticarUsuario(String email, String contraseña) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> passwordEncoder.matches(contraseña, usuario.getContrasena()));  // Comparamos la contraseña encriptada
    }

    // Obtener usuario por su email
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con email: " + email));
    }

    // Obtener usuario por su ID
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
    }
}
