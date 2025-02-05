package br.com.system.dothours.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    
    public Projeto create(Projeto projeto, Long usuarioResponsavelId) {
        
        Usuario usuarioResponsavel = usuarioRepository.findById(usuarioResponsavelId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + usuarioResponsavelId));


        projeto.setId_usuario_responsavel(usuarioResponsavelId);
        projeto.setData_criacao(LocalDate.now()); 

        return projetoRepository.save(projeto);

    }

    
    public List<Projeto> findAll() {

        return projetoRepository.findAll();

    }

   
    public Optional<Projeto> findById(Long id) {

        return projetoRepository.findById(id);

    }

     /**
         * Atualiza um projeto existente com novos dados.
         *
         * @param id O ID do projeto a ser atualizado.
         * @param projetoAtualizado Objeto contendo as novas informações do projeto.
         * @return O projeto atualizado salvo no banco de dados.
         * @throws RuntimeException Se o projeto com o ID fornecido não for encontrado.
     **/
    public Projeto update(Long id, Projeto projetoAtualizado) {

        return projetoRepository.findById(id).map(projeto -> {
            projeto.setNome(projetoAtualizado.getNome());
            projeto.setDescricao(projetoAtualizado.getDescricao());
            projeto.setData_inicio(projetoAtualizado.getData_inicio());
            projeto.setData_fim(projetoAtualizado.getData_fim());
            projeto.setStatus(projetoAtualizado.getStatus());
            projeto.setPrioridade(projetoAtualizado.getPrioridade());
            projeto.setId_usuario_responsavel(projetoAtualizado.getId_usuario_responsavel());

            return projetoRepository.save(projeto);
        }).orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

    }

    
    public void delete(Long id) {
        
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto não encontrado com ID: " + id);
        }

    }


    public Projeto salvarProjeto(ProjetoDTO projetoDTO) {
        Usuario usuario = usuarioRepository.findById(projetoDTO.getId_usuario_responsavel())
                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setData_inicio(projetoDTO.getData_inicio());
        projeto.setData_fim(projetoDTO.getData_fim());
        projeto.setStatus(projetoDTO.getStatus());
        projeto.setId_usuario_responsavel(usuario);
        projeto.setData_criacao(projetoDTO.getData_criacao());
        projeto.setPrioridade(projetoDTO.getPrioridade());

        return projetoRepository.save(projeto);
    }


}
