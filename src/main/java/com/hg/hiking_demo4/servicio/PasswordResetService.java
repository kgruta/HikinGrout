package com.hg.hiking_demo4.servicio;

import com.hg.hiking_demo4.entidades.PasswordResetToken;
import com.hg.hiking_demo4.entidades.Usuario;
import com.hg.hiking_demo4.repositorios.PasswordResetTokenRepository;
import com.hg.hiking_demo4.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordResetService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    // Define el remitente verificado
    private static final String REMITENTE = "hikingrout@gmail.com"; // Reemplaza con tu correo verificado

    public void crearTokenPasswordReset(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No existe un usuario con ese email"));

        // Eliminar token existente si existe
        passwordResetTokenRepository.findByUsuario(usuario).ifPresent(passwordResetTokenRepository::delete);

        String token = UUID.randomUUID().toString();

        PasswordResetToken prt = new PasswordResetToken();
        prt.setUsuario(usuario);
        prt.setToken(token);
        prt.setFechaExpiracion(LocalDateTime.now().plusMinutes(30));
        passwordResetTokenRepository.save(prt);

        enviarEmailRecuperacion(usuario.getEmail(), token);
    }

    private void enviarEmailRecuperacion(String email, String token) {
        String link = "http://localhost:8080/restablecer?token=" + token;
        String htmlContent = "<p>Hola,</p>"
                + "<p>Has solicitado restablecer tu contraseña. Haz clic en el enlace de abajo para restablecerla:</p>"
                + "<a href=\"" + link + "\">Restablecer Contraseña</a>"
                + "<p>El enlace expirará en 30 minutos.</p>"
                + "<p>Si no solicitaste este cambio, ignora este mensaje.</p>";

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(REMITENTE); // Establece el remitente verificado
            message.setTo(email);
            message.setSubject("Recuperación de Contraseña");
            message.setText("Hola,\n\nHas solicitado restablecer tu contraseña. Haz clic en el siguiente enlace para restablecerla:\n" + link + "\n\nEl enlace expirará en 30 minutos.\n\nSi no solicitaste este cambio, ignora este mensaje.");

            mailSender.send(message);
            log.info("Correo de recuperación enviado a {}", email);
        } catch (Exception e) {
            log.error("Error al enviar el correo de recuperación a {}: {}", email, e.getMessage());
            throw new RuntimeException("Error al enviar el correo de recuperación.", e);
        }
    }

    public Usuario validarToken(String token) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token no válido"));

        if (prt.isExpirado()) {
            throw new IllegalArgumentException("Token expirado");
        }

        return prt.getUsuario();
    }

    public void restablecerContrasena(String token, String nuevaContrasena) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token no válido"));

        if (prt.isExpirado()) {
            throw new IllegalArgumentException("Token expirado");
        }

        Usuario usuario = prt.getUsuario();
        usuario.setContrasena(passwordEncoder.encode(nuevaContrasena));
        usuarioRepository.save(usuario);

        passwordResetTokenRepository.delete(prt);
    }

    // Método adicional para enviar correos de prueba
    public void enviarCorreoDePrueba(String destinatario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(REMITENTE); // Remitente verificado
        message.setTo(destinatario);
        message.setSubject("Correo de Prueba");
        message.setText("Si estás viendo este mensaje, la configuración SMTP funciona correctamente.");

        try {
            mailSender.send(message);
            log.info("Correo de prueba enviado a {}", destinatario);
        } catch (Exception e) {
            log.error("Error al enviar el correo de prueba a {}: {}", destinatario, e.getMessage());
            throw new RuntimeException("Error al enviar el correo de prueba.", e);
        }
    }
}
