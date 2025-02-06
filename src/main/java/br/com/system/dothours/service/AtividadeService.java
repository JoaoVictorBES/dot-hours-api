package br.com.system.dothours.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.AtividadeRepository;


/**
 * Serviço responsável pela lógica de negócios relacionada à manipulação de atividades.
 * Contém métodos para criação, leitura, atualização e exclusão de atividades, além de métodos 
 * adicionais para busca de atividades por projeto e usuário responsável.
 */
@Service
public class AtividadeService {

   @Autowired
    private AtividadeRepository atividadesRepository;



     /**
     * Cria uma nova atividade a partir de um DTO e salva no repositório.
     *
     * @param atividadeDTO DTO contendo os dados da atividade a ser criada.
     * @return O DTO da atividade recém-criada.
     * @throws RuntimeException Se ocorrer algum erro ao salvar a atividade.
     */
    public AtividadeDTO create(AtividadeDTO atividadeDTO) {
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setDataInicio(atividadeDTO.getDataInicio());
        atividade.setDataFim(atividadeDTO.getDataFim());
        atividade.setStatus(atividadeDTO.getStatus());
        atividade.setDataCriacao(LocalDateTime.now());
        Atividade atividadeSalva = atividadesRepository.save(atividade);
        return convertToDTO(atividadeSalva);
    }



    /**
     * Retorna uma lista de todas as atividades cadastradas.
     *
     * @return Lista de DTOs das atividades.
     */
    public List<AtividadeDTO> findAll() {
        return atividadesRepository.findAll().stream().map(this::convertToDTO).toList();
    }



    /**
     * Retorna a atividade com o ID especificado.
     *
     * @param id O ID da atividade a ser recuperada.
     * @return Um Optional contendo o DTO da atividade, ou um Optional vazio se não for encontrada.
     */
    public Optional<AtividadeDTO> findById(Long id) {
        return atividadesRepository.findById(id).map(this::convertToDTO);
    }



     /**
     * Atualiza os dados de uma atividade existente.
     *
     * @param id O ID da atividade a ser atualizada.
     * @param atividadeDTO DTO contendo os novos dados da atividade.
     * @return O DTO da atividade atualizada.
     * @throws RuntimeException Se a atividade com o ID especificado não for encontrada.
     */
    public AtividadeDTO update(Long id, AtividadeDTO atividadeDTO) {
        return atividadesRepository.findById(id).map(atividade -> {
            atividade.setNome(atividadeDTO.getNome());
            atividade.setDescricao(atividadeDTO.getDescricao());
            atividade.setDataInicio(atividadeDTO.getDataInicio());
            atividade.setDataFim(atividadeDTO.getDataFim());
            atividade.setStatus(atividadeDTO.getStatus());
            Atividade updatedAtividade = atividadesRepository.save(atividade);
            return convertToDTO(updatedAtividade);
        }).orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));
    }



    /**
     * Exclui a atividade com o ID especificado.
     *
     * @param id O ID da atividade a ser excluída.
     * @throws RuntimeException Se a atividade com o ID especificado não for encontrada.
     */
    public void delete(Long id) {
        if (atividadesRepository.existsById(id)) {
            atividadesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Atividade não encontrada com ID: " + id);
        }
    }



    /**
     * Converte uma entidade Atividade para um DTO.
     *
     * @param atividade A entidade Atividade a ser convertida.
     * @return O DTO correspondente à entidade Atividade.
     */
    private AtividadeDTO convertToDTO(Atividade atividade) {
        return new AtividadeDTO(
                atividade.getId(),
                atividade.getNome(),
                atividade.getDescricao(),
                atividade.getDataInicio(),
                atividade.getDataFim(),
                atividade.getStatus(),
                atividade.getProjeto().getId(),
                atividade.getUsuarioResponsavel().getId(),
                atividade.getDataCriacao()
        );
    }



    
     /**
     * Retorna uma lista de atividades associadas a um projeto específico.
     *
     * @param id_projeto O projeto para o qual as atividades serão recuperadas.
     * @return Lista de DTOs das atividades associadas ao projeto.
     */
    public List<AtividadeDTO> findByProjeto(Projeto id_projeto) {
        return atividadesRepository.findByProjeto(id_projeto)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }




    /**
     * Retorna uma lista de atividades associadas a um usuário responsável específico.
     *
     * @param usuarioResponsavel O ID do usuário responsável pelas atividades.
     * @return Lista de DTOs das atividades associadas ao usuário.
     */
    public List<AtividadeDTO> findByUsuarioResponsavel(Long usuarioResponsavel) {
        
        Usuario usuario = new Usuario();
        usuario.setId(usuarioResponsavel);
        return atividadesRepository.findByUsuarioResponsavel(usuario)
                .stream()
                .map(this::convertToDTO)
                .toList();

    }

}
