package br.com.system.dothours.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.system.dothours.Enum.Role;
import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.dto.UsuarioDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.AtividadeRepository;
import br.com.system.dothours.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
   

    /**
     * Cria um novo usuário no sistema, aplicando validações e configurações padrão.
     *
     * Este método valida os campos obrigatórios do usuário, define valores padrão para
     * determinados atributos (como função e data de criação), codifica a senha antes de salvar
     * e retorna um {UsuarioDTO} com os dados do usuário salvo.
     *
     * @param usuario O objeto {@link Usuario} contendo os dados do usuário a ser criado.
     * @return Um {UsuarioDTO} representando o usuário salvo no banco de dados.
     * @throws IllegalArgumentException Se algum dos campos obrigatórios (username, email ou password) estiver ausente ou vazio.
     */
    public UsuarioDTO create(Usuario usuario) {

        if (usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username é obrigatório");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email é obrigatório");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password é obrigatório");
        }
    
        if (usuario.getRole() == null ) {
            usuario.setRole(Role.USER);
        }
    
        
    
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setDataCriacao(LocalDateTime.now());
    
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
    
    
        return UsuarioDTO.fromEntity(usuarioSalvo);
    }


    /**
     * Retorna uma lista de todos os usuários cadastrados.
     *
     * @return Lista de {@link UsuarioDTO}.
     */
      public Page<UsuarioDTO> findAll(Pageable pageable) {
        // Chama o findAll no repositório e converte os resultados
        Page<Usuario> usuarioPage = usuarioRepository.findAll(pageable);
        return usuarioPage.map(UsuarioDTO::fromEntity);
    }

    public List<UsuarioDTO> listAll() {
        // Busca todos os usuários no repositório
        List<Usuario> usuarios = usuarioRepository.findAll();
    
        // Converte a lista de Usuario para UsuarioDTO
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
            .map(usuario -> new UsuarioDTO(
                usuario.getId(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
            ))
            .collect(Collectors.toList());
    
        return usuariosDTO;
    }


     /**
     * Busca um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser buscado.
     * @return {@link Optional} contendo {@link UsuarioDTO}, se encontrado.
     */
    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::fromEntity);
    }


     /**
     * Atualiza os dados de um usuário existente.
     *
     * @param id ID do usuário a ser atualizado.
     * @param usuarioCreateDTO DTO contendo os novos dados do usuário.
     * @return {@link UsuarioDTO} atualizado.
     * @throws RuntimeException Se o usuário não for encontrado.
     */
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setUsername(usuarioDTO.getUsername());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setRole(usuarioDTO.getRole());

            usuarioRepository.save(usuario);
            return UsuarioDTO.fromEntity(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
    }


     /**
     * Exclui um usuário pelo seu ID.
     *
     * @param id ID do usuário a ser removido.
     * @throws RuntimeException Se o usuário não for encontrado.
     */
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o username: " + username));
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }


    public UserDetails getUsuarioLogado() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
            if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
                return (UserDetails) authentication.getPrincipal();
            }
            
            throw new RuntimeException("Usuário não está autenticado.");
    }

    public List<UsuarioDTO> findByFilters(String nome, Role role, Atividade atividade, LocalDateTime ultimoLogin) {
        
        List<Usuario> usuarios;

        if (role != null) {
            usuarios = usuarioRepository.findByRole(role);
        } else if (atividade != null) {
           usuarios = usuarioRepository.findUsuariosByAtividade(atividade.getId());
        } else if (ultimoLogin != null) {
            usuarios = usuarioRepository.findByUltimoLoginAfter(ultimoLogin);
        } else {
            usuarios = usuarioRepository.findAll();
        }

        return usuarios.stream().map(UsuarioDTO::fromEntity).collect(Collectors.toList());
    }

    public List<AtividadeDTO> listarAtividadesPorUsuario(Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        Usuario usuario = usuarioOpt.get();
        List<Atividade> atividades = atividadeRepository.findByUsuarioResponsavel(usuario);

        return atividades.stream().map(AtividadeDTO::fromEntity).collect(Collectors.toList());
    }


}
