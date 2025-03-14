package br.com.system.dothours.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.system.dothours.model.LancamentoHoras;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long> {

    List<LancamentoHoras> findByUsuarioId(Long idUsuario);

    List<LancamentoHoras> findByAtividadeNome(String nomeAtividade);

    List<LancamentoHoras> findByDataRegistro(LocalDate dataRegistro);

    List<LancamentoHoras> findByUsuarioIdAndDataRegistro(Long idUsuario, LocalDate dataRegistro);

    List<LancamentoHoras> findByAtividadeId(Long idAtividade);

    Page<LancamentoHoras> findAll(Pageable pageable);

    List<LancamentoHoras> findByAtividadeNomeAndDataRegistro(String nomeAtividade, LocalDate dataRegistro);

    @Query("SELECT lh FROM LancamentoHoras lh WHERE " +
           "(:idUsuario IS NULL OR lh.usuario.id = :idUsuario) AND " +
           "(:idAtividade IS NULL OR lh.atividade.id = :idAtividade) AND " +
           "(:nomeAtividade IS NULL OR lh.atividade.nome = :nomeAtividade) AND " +
           "(:data IS NULL OR lh.dataRegistro = :data)")
    List<LancamentoHoras> buscarLancamentos(@Param("idUsuario") Long idUsuario, 
                                            @Param("idAtividade") Long idAtividade,
                                            @Param("nomeAtividade") String nomeAtividade, 
                                            @Param("data") LocalDate dataRegistro);

       
}

