package br.com.system.dothours.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.UsuarioCriadoDTO;
import br.com.system.dothours.dto.UsuarioDTO;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    /**
     * Cria um novo usuário no sistema.
     *
     * @param usuarioCreateDTO DTO contendo os dados do usuário a ser criado.
     * @return {@link UsuarioDTO} representando o usuário criado.
     */
    public UsuarioDTO create(UsuarioCriadoDTO usuarioCreateDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCreateDTO.getNome());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setSenha(usuarioCreateDTO.getSenha()); 
        usuario.setRole(usuarioCreateDTO.getRole());
        usuario.setDataCriaçao(LocalDateTime.now());

        usuario = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(usuario);
    }


    /**
     * Retorna uma lista de todos os usuários cadastrados.
     *
     * @return Lista de {@link UsuarioDTO}.
     */
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                                .stream()
                                .map(UsuarioDTO::fromEntity)
                                .collect(Collectors.toList());
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
    public UsuarioDTO update(Long id, UsuarioCriadoDTO usuarioCreateDTO) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioCreateDTO.getNome());
            usuario.setEmail(usuarioCreateDTO.getEmail());
            usuario.setSenha(usuarioCreateDTO.getSenha());
            usuario.setRole(usuarioCreateDTO.getRole());

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

}
