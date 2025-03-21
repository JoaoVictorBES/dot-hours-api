package br.com.system.dothours.service;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.system.dothours.Enum.PrioridadeProjeto;
import br.com.system.dothours.Enum.StatusAtividade;
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
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }
        String username = authentication.getName();

        // Buscar o usuário no banco
        Usuario usuarioResponsavel = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));

        // Validação do projeto
        if (atividadeDTO.getIdProjetoVinculado() == null) {
            throw new IllegalArgumentException("O projeto não pode ser nulo ou vazio.");
        }

        if (atividadeDTO.getDataCriacao() == null) {
            atividadeDTO.setDataCriacao(LocalDate.now());
        }

        Projeto projeto = projetoRepository.findById(atividadeDTO.getIdProjetoVinculado())
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        // Criando nova atividade
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setDataInicio(atividadeDTO.getDataInicio());
        atividade.setDataFim(atividadeDTO.getDataFim());
        atividade.setStatus(StatusAtividade.valueOf(atividadeDTO.getStatus().name()));
        atividade.setDataCriacao(LocalDate.now());
        atividade.setProjeto(projeto);
        atividade.setUsuarioResponsavel(usuarioResponsavel);
        atividade.setAtivo(true);
        atividade.setAtivo(atividadeDTO.getAtivo() != null ? atividadeDTO.getAtivo() : true);


        // << Agora está inicializado corretamente

        Atividade atividadeSalva = atividadesRepository.save(atividade);

        // Associando usuários vinculados à atividade
        if (atividadeDTO.getUsuarioVinculado() != null && !atividadeDTO.getUsuarioVinculado().isEmpty()) {
            List<AtividadeUsuario> atividadeUsuarios = atividadeDTO.getUsuarioVinculado().stream()
                .map(usuarioDTO -> {
            Usuario usuario = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioDTO.getId() + " não encontrado"));

                AtividadeUsuario atividadeUser = new AtividadeUsuario();
                atividadeUser.setUsuario(usuario);
                atividadeUser.setAtividade(atividadeSalva);
                atividadeUser.setAtivo(true);
                return atividadeUser;
            })
            .collect(Collectors.toList());

            atividadeUsuarioRepository.saveAll(atividadeUsuarios);
        }

        return convertToDTO(atividadeSalva);
    }

    


    /**
     * Retorna uma lista de todas as atividades cadastradas.
     *
     * @return Lista de DTOs das atividades.
     */
    public Page<AtividadeDTO> findAll(Pageable pageable) {
        return atividadesRepository.findAll(pageable)
         .map(this::convertToDTO) ;
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

    public List<AtividadeDTO> findAtividadeByIdUsuario(Long id) {

       // Obtém a lista de atividades do repositório
        List<Atividade> atividades = atividadesRepository.findAtividadeByIdUsuario(id);

        
        // Converte cada Atividade para AtividadeDTO
        List <AtividadeDTO> atividadeDTO = atividades.stream()
                .map(this::convertToDTO) // Usa o método convertToDTO para cada Atividade
                .collect(Collectors.toList()); // Coleta os resultados em uma lista de AtividadeDTO

        for(AtividadeDTO  atividade: atividadeDTO){
          atividade.setHorasAtividade(atividadesRepository.findTotalHorasAtividadeByUser(id, atividade.getId()));
        }

        return atividadeDTO;
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
        dto.setDataCriacao(atividade.getDataCriacao());
        dto.setNomeUsuarioResponsavel(atividade.getUsuarioResponsavel().getUsername());
    
        // Prevenção contra NullPointerException
        if (atividade.getUsuarioResponsavel() != null) {
            dto.setIdUsuarioResponsavel(atividade.getUsuarioResponsavel().getId());
        } else {
            dto.setIdUsuarioResponsavel(null); // Ou trate como necessário
        }
    
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
    
            dto.setProjetoVinculado(Collections.singletonList(projetoDTO));
        }
    
        // Convertendo usuários vinculados para DTO
        List<UsuarioDTO> usuariosDTO = atividadeUsuarioRepository.findByAtividade(atividade)
            .stream()
            .map(atividadeUsuario -> {
                Usuario usuario = atividadeUsuario.getUsuario();
                UsuarioDTO usuarioDTO = new UsuarioDTO(null, null, null, null, null, null);
                usuarioDTO.setId(usuario.getId());
                usuarioDTO.setUsername(usuario.getUsername());
                usuarioDTO.setEmail(usuario.getEmail());
                usuarioDTO.setUltimoLogin(usuario.getUltimoLogin());
                return usuarioDTO;
            })
            .collect(Collectors.toList());
    
        dto.setUsuarioVinculado(usuariosDTO);
    
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

    public void alterarStatusAtividade(Long id, boolean ativo) {
        Atividade atividade = atividadesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada!"));

        atividade.setAtivo(ativo);
        atividadesRepository.save(atividade);
    }


    public List<AtividadeDTO> listarAtividadesAtivas() {
        List<AtividadeDTO> atividades = atividadesRepository.findByAtivoTrue().stream().map(this::convertToDTO).toList();
        System.out.println("Atividades ativas encontradas: " + atividades.size());
        return atividades;
    }

    /**
     * Busca atividades com base em filtros.
     * 
     * @param nome O nome da atividade (opcional).
     * @param status O status da atividade (opcional).
     * @param prioridade A prioridade do projeto (opcional).
     * @param dataInicio A data de início da atividade (opcional).
     * @param dataFim A data de fim da atividade (opcional).
     * @return Lista de atividades que atendem aos filtros.
     */
    public List<AtividadeDTO> findByFilters(String nome, StatusAtividade status, 
                                            PrioridadeProjeto prioridade, LocalDate dataInicio, 
                                            LocalDate dataFim) {
        List<Atividade> atividades = atividadesRepository.findByFilters(nome, status, prioridade, dataInicio, dataFim);
        return atividades.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Page<AtividadeDTO> findAllAtivas(Pageable pageable) {
        return atividadesRepository.findByAtivoTrue(pageable)
               .map(this::convertToDTO);
    }

    public String findTotalHorasAtividadeByUser(Long idUsuario, Long idAtividade) {
        Long totalSegundos = atividadesRepository.findTotalHorasAtividadeByUser(idUsuario, idAtividade);
    
        // Se o total de segundos for nulo ou zero, retorna "00:00:00"
        if (totalSegundos == null || totalSegundos == 0) {
            return "00:00:00";
        }

        // Convertendo a duração de segundos para formato HH:mm
        Long horas = totalSegundos / 3600;
        Long minutos = (totalSegundos % 3600) / 60;
        Long segundos = totalSegundos % 60;
    
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    

}
