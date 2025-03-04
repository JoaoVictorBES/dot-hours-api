package br.com.system.dothours.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import br.com.system.dothours.model.LancamentoHoras;

public interface LancamentoHorasRepository extends JpaRepository<LancamentoHoras, Long> {

    List<LancamentoHoras> findByUsuarioId(Long idUsuario);

    List<LancamentoHoras> findByAtividadeNome(String nomeAtividade);

    @Query("SELECT lh FROM LancamentoHoras lh WHERE lh.usuario.id = :idUsuario AND lh.atividade.nome = :nomeAtividade")
    List<LancamentoHoras> findByUsuarioIdAndAtividadeNome(@Param("idUsuario") Long idUsuario, 
                                                          @Param("nomeAtividade") String nomeAtividade);

}
