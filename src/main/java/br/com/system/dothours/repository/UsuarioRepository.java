package br.com.system.dothours.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.Enum.Role;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByRecoveryToken(String token);
    
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    // Filtro por role
    List<Usuario> findByRole(Role role);

    // Filtro por atividade
    List<Usuario> findByAtividades(Atividade atividade);

    // Filtro por último login (antes ou depois de uma data)
    List<Usuario> findByUltimoLoginAfter(LocalDateTime data);

}
