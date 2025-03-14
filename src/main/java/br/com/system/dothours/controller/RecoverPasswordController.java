package br.com.system.dothours.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.repository.UsuarioRepository;
import br.com.system.dothours.service.PasswordResetService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") 
public class RecoverPasswordController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordResetService passwordResetService;

    private static final Logger logger = LoggerFactory.getLogger(RecoverPasswordController.class);
   
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
    
        if (email == null) {
            return ResponseEntity.badRequest().body("O e-mail não foi fornecido.");
        }
    
        passwordResetService.sendPasswordResetEmail(email);
        return ResponseEntity.ok("E-mail de recuperação enviado.");
    }
    

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }

}
