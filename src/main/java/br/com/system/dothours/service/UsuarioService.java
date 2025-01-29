package br.com.system.dothours.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuariosRepository;

    public Usuario create(Usuario usuario) {

        return usuariosRepository.save(usuario);

    }

    public List<Usuario> findAll() {

        return usuariosRepository.findAll();

    }

    public Optional<Usuario> findById(Long id) {

        return usuariosRepository.findById(id);

    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {

        return usuariosRepository.findById(id).map(usuario -> {

            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            
            return usuariosRepository.save(usuario);

        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

    }

    public void delete(Long id) {

        if (usuariosRepository.existsById(id)) {

            usuariosRepository.deleteById(id);

        } else {

            throw new RuntimeException("Usuário não encontrado com ID: " + id);

        }

    }

}
