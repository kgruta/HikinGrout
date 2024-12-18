package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    private static final String REMITENTE = "hikingrout@gmail.com"; // Replace with your email

    @Transactional
    public Usuario actualizarPerfil(Usuario usuario) {
        // Save updated user information
        Usuario updatedUsuario = usuarioRepository.save(usuario);

        // Send email confirmation
        enviarCorreoActualizacion(usuario);

        return updatedUsuario;
    }

    private void enviarCorreoActualizacion(Usuario usuario) {
        String email = usuario.getEmail();
        String subject = "Perfil Actualizado";
        String text = "Hola " + usuario.getNombre() + ",\n\n" +
                "Tu perfil ha sido actualizado con éxito. Si no realizaste este cambio, por favor, contacta con soporte.";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(REMITENTE);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }
}
