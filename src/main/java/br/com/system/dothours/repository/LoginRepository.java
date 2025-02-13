package br.com.system.dothours.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsername(String username);

}
