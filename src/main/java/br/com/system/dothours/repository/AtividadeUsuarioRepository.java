package br.com.system.dothours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.AtividadeUsuario;

public interface AtividadeUsuarioRepository extends JpaRepository<AtividadeUsuario, Long>{

    List<AtividadeUsuario> findByAtividade(Atividade atividade);


}
