package com.hg.hiking_demo4.seguridad;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + username));

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getContrasena())
                .roles("USER")
                .build();
    }
}
