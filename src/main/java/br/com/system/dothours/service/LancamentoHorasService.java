package br.com.system.dothours.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.repository.LancamentoHorasRepository;

@Service
public class LancamentoHorasService {

    @Autowired
    private LancamentoHorasRepository lancamentoHorasRepository;

   // @Autowired
    //private UsuarioService usuarioService;

      /**
     * Cria um novo registro de lançamento de horas.
     * A data de registro é automaticamente definida para o momento atual.
     *
     * @param lancamentoHoras Objeto {@link LancamentoHoras} contendo os detalhes do lançamento.
     * @return {@link LancamentoHoras} salvo no banco de dados.
     */
    public LancamentoHoras create(LancamentoHoras lancamentoHoras) {
        // Se o ID do usuário foi enviado pelo frontend, mantém; senão, deixa nulo
        if (lancamentoHoras.getUsuario() == null || lancamentoHoras.getUsuario().getId() == null) {
            lancamentoHoras.setUsuario(null); // Lançamento sem usuário autenticado
        }

        lancamentoHoras.setDataRegistro(LocalDate.now());
        return lancamentoHorasRepository.save(lancamentoHoras);

    }

    

     /**
     * Retorna todos os lançamentos de horas cadastrados.
     *
     * @return Lista de {@link LancamentoHoras}.
     */
    public List<LancamentoHoras> findAll() {

        return lancamentoHorasRepository.findAll();

    }

    

    /**
     * Busca um lançamento de horas pelo seu ID.
     *
     * @param id ID do lançamento de horas a ser buscado.
     * @return {@link Optional} contendo {@link LancamentoHoras}, se encontrado.
     */
    public Optional<LancamentoHoras> findById(Long id) {

        return lancamentoHorasRepository.findById(id);

    }

    

     /**
     * Atualiza um lançamento de horas existente com novos dados.
     *
     * @param id ID do lançamento de horas a ser atualizado.
     * @param lancamentoAtualizado Objeto contendo as novas informações do lançamento.
     * @return {@link LancamentoHoras} atualizado e salvo no banco de dados.
     * @throws RuntimeException Se o lançamento de horas com o ID fornecido não for encontrado.
     */
    public LancamentoHoras update(Long id, LancamentoHoras lancamentoAtualizado) {

        return lancamentoHorasRepository.findById(id).map(lancamento -> {
            lancamento.setAtividade(lancamentoAtualizado.getAtividade());
            lancamento.setUsuario(lancamentoAtualizado.getUsuario());
            lancamento.setDescricao(lancamentoAtualizado.getDescricao());
            lancamento.setDataInicio(lancamentoAtualizado.getDataInicio());
            lancamento.setDataFim(lancamentoAtualizado.getDataFim());
            return lancamentoHorasRepository.save(lancamento);
        }).orElseThrow(() -> new RuntimeException("Lançamento de horas não encontrado com ID: " + id));

    }

    
    /**
     * Exclui um lançamento de horas pelo seu ID.
     *
     * @param id ID do lançamento de horas a ser removido.
     * @throws RuntimeException Se o lançamento de horas não for encontrado.
     */
    public void delete(Long id) {

        if (lancamentoHorasRepository.existsById(id)) {
            lancamentoHorasRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lançamento de horas não encontrado com ID: " + id);
        }

    }

    public List<LancamentoHoras> buscarLancamentos(Long idUsuario, Long idAtividade, String nomeAtividade, LocalDate dataRegistro) {
        if (idUsuario != null || idAtividade != null || nomeAtividade != null || dataRegistro != null) {
            return lancamentoHorasRepository.buscarLancamentos(idUsuario, idAtividade, nomeAtividade, dataRegistro);
        } 
        return lancamentoHorasRepository.findAll();
    }
    

}
