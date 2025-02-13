package br.com.system.dothours.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByRecoveryToken(String token);
    
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

}
