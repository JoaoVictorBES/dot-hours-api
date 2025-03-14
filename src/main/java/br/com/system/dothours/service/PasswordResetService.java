package br.com.system.dothours.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.PasswordResetToken;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.PasswordResetTokenRepository;
import br.com.system.dothours.repository.UsuarioRepository;

@Service
public class PasswordResetService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    public void sendPasswordResetEmail(String email) {
        Usuario user = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(token, user);
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:4200/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), "Recuperação de Senha", "Clique no link para redefinir sua senha: " + resetLink);
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
            .orElseThrow(() -> new IllegalArgumentException("Token inválido ou expirado"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token expirado.");
        }

        Usuario user = resetToken.getUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        usuarioRepository.save(user);

        tokenRepository.delete(resetToken);
    }
    

        //String resetLink = "http://localhost:4200/reset-password?token=" + token;
        //emailService.sendEmail(email, "Recuperação de senha", "Clique no link para redefinir sua senha. " + resetLink);
    

}
