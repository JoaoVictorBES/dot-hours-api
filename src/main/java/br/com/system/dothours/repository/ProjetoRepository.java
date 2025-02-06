package br.com.system.dothours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


}
