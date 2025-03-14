package br.com.system.dothours.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.PasswordResetToken;
import br.com.system.dothours.model.Usuario;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // Busca um token de redefinição de senha pelo valor do token
    Optional<PasswordResetToken> findByToken(String token);
    
    // Busca um token de redefinição de senha relacionado a um usuário
    Optional<PasswordResetToken> findByUser(Usuario user);
    
    // Método adicional para limpar tokens expirados, se necessário
    void deleteByExpiryDateBefore(LocalDateTime expiryDate);

}
