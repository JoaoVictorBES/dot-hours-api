package br.com.system.dothours.service;

import java.time.LocalDate;
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

    public UsuarioDTO create(UsuarioCriadoDTO usuarioCreateDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioCreateDTO.getNome());
        usuario.setEmail(usuarioCreateDTO.getEmail());
        usuario.setSenha(usuarioCreateDTO.getSenha()); // Idealmente, você deve criptografar a senha antes de salvar
        usuario.setRole(usuarioCreateDTO.getRole());
        usuario.setData_criaçao(LocalDate.now());

        usuario = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(usuario);
    }

    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll()
                                .stream()
                                .map(UsuarioDTO::fromEntity)
                                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> findById(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::fromEntity);
    }

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

    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

}
