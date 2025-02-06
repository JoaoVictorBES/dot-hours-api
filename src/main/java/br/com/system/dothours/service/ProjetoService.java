package br.com.system.dothours.service;

import java.time.LocalDate;
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

    public ProjetoDTO create(ProjetoDTO projetoDTO) {
        Usuario usuario = usuarioRepository.findById(projetoDTO.getId_usuario_responsavel())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Projeto projeto = new Projeto();
        projeto.setNome(projetoDTO.getNome());
        projeto.setDescricao(projetoDTO.getDescricao());
        projeto.setData_inicio(projetoDTO.getData_inicio());
        projeto.setData_fim(projetoDTO.getData_fim());
        projeto.setStatus(projetoDTO.getStatus());
        projeto.setUsuarioResponsavel(usuario);
        projeto.setData_criacao(LocalDate.now());
        projeto.setPrioridade(projetoDTO.getPrioridade());

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return convertToDTO(projetoSalvo);
    }


    
    public List<ProjetoDTO> findAll() {

        return projetoRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

   
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
            projeto.setData_inicio(projetoDTO.getData_inicio());
            projeto.setData_fim(projetoDTO.getData_fim());
            projeto.setStatus(projetoDTO.getStatus());
            projeto.setPrioridade(projetoDTO.getPrioridade());
            projeto.setId_usuario_responsavel(projetoDTO.getId_usuario_responsavel());

            return projetoRepository.save(projeto);
        }).orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + id));

        return convertToDTO(projetoAtualizado);

    }

    
    public void delete(Long id) {
        
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto não encontrado com ID: " + id);
        }

    }


    private ProjetoDTO convertToDTO(Projeto projeto) {

        ProjetoDTO projetoDTO = new ProjetoDTO();
        projetoDTO.setId(projeto.getId());
        projetoDTO.setNome(projeto.getNome());
        projetoDTO.setDescricao(projeto.getDescricao());
        projetoDTO.setData_inicio(projeto.getData_inicio());
        projetoDTO.setData_fim(projeto.getData_fim());
        projetoDTO.setStatus(projeto.getStatus());
        projetoDTO.setPrioridade(projeto.getPrioridade());
        projetoDTO.setId_usuario_responsavel(projeto.getUsuarioResponsavel().getId());
        return projetoDTO;

    }

}
