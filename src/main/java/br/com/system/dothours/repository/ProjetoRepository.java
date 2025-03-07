package br.com.system.dothours.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.dothours.Enum.PrioridadeProjeto;
import br.com.system.dothours.Enum.StatusProjeto;
import br.com.system.dothours.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByNomeContainingIgnoreCase(String nome);
    
    List<Projeto> findByStatus(StatusProjeto status);
    
    List<Projeto> findByPrioridade(PrioridadeProjeto prioridade);
    
    List<Projeto> findByDataInicioGreaterThanEqual(LocalDate dataInicio);
    
    @Query("SELECT p FROM Projeto p WHERE (:nome IS NULL OR p.nome LIKE %:nome%) " +
           "AND (:status IS NULL OR p.status = :status) " +
           "AND (:prioridade IS NULL OR p.prioridade = :prioridade) " +
           "AND (:dataInicio IS NULL OR p.dataInicio >= :dataInicio)")
    List<Projeto> findByFilters(@Param("nome") String nome,
                                 @Param("status") StatusProjeto status,
                                 @Param("prioridade") PrioridadeProjeto prioridade,
                                 @Param("dataInicio") LocalDate dataInicio);

}
