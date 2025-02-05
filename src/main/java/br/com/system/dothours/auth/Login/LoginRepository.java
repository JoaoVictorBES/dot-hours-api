package br.com.system.dothours.auth.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String username);

}
