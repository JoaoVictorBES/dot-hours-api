package br.com.system.dothours.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.dothours.Enum.Role;
import br.com.system.dothours.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByRecoveryToken(String token);
    
    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> findAll(Pageable pageable);

    // Filtro por role
    List<Usuario> findByRole(Role role);

    @Query(value = "SELECT u.* FROM sistema_gerenciamento.usuarios u " +
               "JOIN sistema_gerenciamento.atividades_usuarios atv " +
               "ON atv.id_usuario = u.id_usuario " +
               "WHERE atv.id_atividade = :idAtividade", 
       nativeQuery = true)
    List<Usuario> findUsuariosByAtividade(@Param("idAtividade") Long idAtividade);

    

    // Filtro por último login (antes ou depois de uma data)
    List<Usuario> findByUltimoLoginAfter(LocalDateTime data);

}
