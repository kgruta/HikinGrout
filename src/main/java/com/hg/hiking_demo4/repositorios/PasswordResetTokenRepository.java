package com.hg.hiking_demo4.repositorios;



import com.hg.hiking_demo4.entidades.PasswordResetToken;
import com.hg.hiking_demo4.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUsuario(Usuario usuario);
}