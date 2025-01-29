package br.com.system.dothours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.system.dothours.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

}
