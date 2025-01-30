package br.com.system.dothours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Usuario;


public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    List<Atividade> findByIdProjeto(Long id_projeto);

    List<Atividade> findByIdUsuarioResponsavel(Usuario usuarioResponsavel);

}
