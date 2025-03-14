package br.com.system.dothours.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.AtividadeUsuario;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.AtividadeRepository;
import br.com.system.dothours.repository.AtividadeUsuarioRepository;


@Service
public class AtividadeUsuarioService {

    @Autowired
    private AtividadeUsuarioRepository atividadeUsuarioRepository;

    public void save(List<Long> idUsuario, AtividadeDTO atividade){

        if (!idUsuario.isEmpty()){
            for (Long id: idUsuario){
                AtividadeUsuario atividadeUsuario = new AtividadeUsuario();
                Atividade atividadee = new Atividade();
                Usuario usuario = new Usuario();
                atividadee.setId(atividade.getId());
                usuario.setId(id);
                atividadeUsuario.setAtividade(atividadee);
                atividadeUsuario.setUsuario(usuario);
                atividadeUsuario.setAtivo(true);

                atividadeUsuarioRepository.save(atividadeUsuario);
            }
        }

    }

}
