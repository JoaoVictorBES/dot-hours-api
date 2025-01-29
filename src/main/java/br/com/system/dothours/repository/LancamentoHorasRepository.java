package br.com.system.dothours.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.system.dothours.model.LancamentoHoras;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long> {

}
