package br.com.system.dothours.service;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.dto.ProjetoDTO;
import br.com.system.dothours.dto.UsuarioDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.AtividadeUsuario;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.AtividadeRepository;
import br.com.system.dothours.repository.AtividadeUsuarioRepository;
import br.com.system.dothours.repository.ProjetoRepository;
import br.com.system.dothours.repository.UsuarioRepository;


/**
 * Serviço responsável pela lógica de negócios relacionada à manipulação de atividades.
 * Contém métodos para criação, leitura, atualização e exclusão de atividades, além de métodos 
 * adicionais para busca de atividades por projeto e usuário responsável.
 */
@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadesRepository;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AtividadeUsuarioRepository atividadeUsuarioRepository;


     /**
     * Cria uma nova atividade a partir de um DTO e salva no repositório.
     *
     * @param atividadeDTO DTO contendo os dados da atividade a ser criada.
     * @return O DTO da atividade recém-criada.
     * @throws RuntimeException Se ocorrer algum erro ao salvar a atividade.
     */
    public AtividadeDTO create(AtividadeDTO atividadeDTO) {
        // Obtendo o usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
    
        Usuario usuarioResponsavel = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));
    
        // Validação do projeto
        if (atividadeDTO.getIdProjetoVinculado() == null ) {
            throw new IllegalArgumentException("O projeto não pode ser nulo ou vazio.");
        }
    
        //ProjetoDTO projetoDTO = atividadeDTO.getProjetoVinculado().get(0); // Pegando o primeiro projeto da lista
        Projeto projeto = projetoRepository.findById(atividadeDTO.getIdProjetoVinculado())
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
           
        // Criando nova atividade
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setDataInicio(atividadeDTO.getDataInicio());
        atividade.setDataFim(atividadeDTO.getDataFim());
        atividade.setStatus(atividadeDTO.getStatus());
        atividade.setDataCriacao(LocalDate.now());
        atividade.setProjeto(projeto);
        atividade.setUsuarioResponsavel(usuarioResponsavel);
    
        Atividade atividadeSalva = atividadesRepository.save(atividade);

        // Associando usuários vinculados à atividade
       if (atividadeDTO.getUsuarioVinculado() != null && !atividadeDTO.getUsuarioVinculado().isEmpty()) {
        for (UsuarioDTO usuarioDTO : atividadeDTO.getUsuarioVinculado()) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
            .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioDTO.getId() + " não encontrado"));

        AtividadeUsuario atvUser = new AtividadeUsuario();
        atvUser.setUsuario(usuario); // << Aqui agora passa o objeto correto
        atvUser.setAtividade(atividadeSalva); // << Aqui agora passa o objeto correto
        atvUser.setAtivo(true);

        atividadeUsuarioRepository.save(atvUser);
    }
}
    
        // Salvando a atividade no banco
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
        AtividadeDTO dto = new AtividadeDTO();
        dto.setId(atividade.getId());
        dto.setNome(atividade.getNome());
        dto.setDescricao(atividade.getDescricao());
        dto.setDataInicio(atividade.getDataInicio());
        dto.setDataFim(atividade.getDataFim());
        dto.setStatus(atividade.getStatus());
        dto.setIdUsuarioResponsavel(atividade.getUsuarioResponsavel().getId());
        dto.setDataCriacao(atividade.getDataCriacao());
    
        // Convertendo o projeto vinculado para DTO
        if (atividade.getProjeto() != null) {
            ProjetoDTO projetoDTO = new ProjetoDTO();
            projetoDTO.setId(atividade.getProjeto().getId());
            projetoDTO.setNome(atividade.getProjeto().getNome());
            projetoDTO.setDescricao(atividade.getProjeto().getDescricao());
            projetoDTO.setDataInicio(atividade.getProjeto().getDataInicio());
            projetoDTO.setDataFim(atividade.getProjeto().getDataFim());
            projetoDTO.setStatus(atividade.getProjeto().getStatus());
            projetoDTO.setPrioridade(atividade.getProjeto().getPrioridade());
            projetoDTO.setDataCriacao(atividade.getProjeto().getDataCriacao());
    
            dto.setProjetoVinculado(Collections.singletonList(projetoDTO)); // Convertendo para lista
        }
    
        // Convertendo usuários vinculados para DTO
        /*if (atividade.getUsuarios() != null) {
            List<UsuarioDTO> usuariosDTO = atividade.getUsuarios().stream().map(usuario -> {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(usuario.getId());
                usuarioDTO.setUsername(usuario.getUsername());
                usuarioDTO.setEmail(usuario.getEmail());
                usuarioDTO.setUltimoLogin(usuario.getUltimoLogin());
                return usuarioDTO;
            }).collect(Collectors.toList());
    
            dto.setUsuarioVinculado(usuariosDTO);
        }*/
    
        return dto;
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
