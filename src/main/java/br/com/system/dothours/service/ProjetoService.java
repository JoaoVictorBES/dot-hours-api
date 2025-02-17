package br.com.system.dothours.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.ProjetoDTO;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.ProjetoRepository;
import br.com.system.dothours.repository.UsuarioRepository;

@Service
public class ProjetoService {

   

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


     /**
     * Cria um novo projeto associado a um usuário responsável.
     *
     * @param projetoDTO Objeto DTO contendo os dados do projeto a ser criado.
     * @return {@link ProjetoDTO} representando o projeto criado.
     * @throws RuntimeException Se o usuário responsável pelo projeto não for encontrado.
     */
    public ProjetoDTO create(ProjetoDTO projetoDTO) {
        Usuario usuario = usuarioRepository.findById(projetoDTO.getIdUsuarioResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setDataInicio(projetoDTO.getDataInicio());
        projeto.setDataFim(projetoDTO.getDataFim());
        projeto.setStatus(projetoDTO.getStatus());
        projeto.setUsuarioResponsavel(usuario);
        projeto.setDataCriacao(LocalDateTime.now());
        projeto.setPrioridade(projetoDTO.getPrioridade());

        Usuario usuarioResponsavel = usuarioRepository.findById(projetoDTO.getIdUsuarioResponsavel())
            .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));
        projeto.setUsuarioResponsavel(usuarioResponsavel);

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return convertToDTO(projetoSalvo);
    }


    
     /**
     * Retorna uma lista de todos os projetos cadastrados.
     *
     * @return Lista de {@link ProjetoDTO}.
     */
    public List<ProjetoDTO> findAll() {

        return projetoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }



     /**
     * Busca um projeto pelo seu ID.
     *
     * @param id ID do projeto a ser buscado.
     * @return {@link Optional} contendo {@link ProjetoDTO}, se encontrado.
     */
    public Optional<ProjetoDTO> findById(Long id) {

        return projetoRepository.findById(id).map(this::convertToDTO);

    }



     /**
         * Atualiza um projeto existente com novos dados.
         *
         * @param id O ID do projeto a ser atualizado.
         * @param projetoDTO Objeto contendo as novas informações do projeto.
         * @return O projeto atualizado salvo no banco de dados.
         * @throws RuntimeException Se o projeto com o ID fornecido não for encontrado.
     **/
    public ProjetoDTO update(Long id, ProjetoDTO projetoDTO) {

        Projeto projetoAtualizado = projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoDTO.getNome());
            projeto.setDescricao(projetoDTO.getDescricao());
            projeto.setDataInicio(projetoDTO.getDataInicio());
            projeto.setDataFim(projetoDTO.getDataFim());
            projeto.setStatus(projetoDTO.getStatus());
            projeto.setPrioridade(projetoDTO.getPrioridade());
            projeto.setIdUsuarioResponsavel(projetoDTO.getIdUsuarioResponsavel());

            return projetoRepository.save(projeto);
        }).orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

        return convertToDTO(projetoAtualizado);

    }

    

     /**
     * Exclui um projeto pelo seu ID.
     *
     * @param id ID do projeto a ser removido.
     * @throws RuntimeException Se o projeto não for encontrado.
     */
    public void delete(Long id) {
        
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto não encontrado com ID: " + id);
        }

    }


    
     /**
     * Converte uma entidade {@link Projeto} para um DTO {@link ProjetoDTO}.
     *
     * @param projeto Objeto {@link Projeto} a ser convertido.
     * @return Objeto {@link ProjetoDTO} convertido.
     */
    private ProjetoDTO convertToDTO(Projeto projeto) {

        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(projeto.getId());
        projetoDTO.setNome(projeto.getNome());
        projetoDTO.setDescricao(projeto.getDescricao());
        projetoDTO.setDataInicio(projeto.getDataInicio());
        projetoDTO.setDataFim(projeto.getDataFim());
        projetoDTO.setStatus(projeto.getStatus());
        projetoDTO.setPrioridade(projeto.getPrioridade());
        projetoDTO.setIdUsuarioResponsavel(projeto.getUsuarioResponsavel().getId());
        return projetoDTO;

    }

}
