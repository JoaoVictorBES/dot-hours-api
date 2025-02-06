package br.com.system.dothours.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.AtividadeRepository;

@Service
public class AtividadeService {

   @Autowired
    private AtividadeRepository atividadesRepository;

    public AtividadeDTO create(AtividadeDTO atividadeDTO) {
        Atividade atividade = new Atividade();
        atividade.setNome(atividadeDTO.getNome());
        atividade.setDescricao(atividadeDTO.getDescricao());
        atividade.setDataInicio(atividadeDTO.getDataInicio());
        atividade.setDataFim(atividadeDTO.getDataFim());
        atividade.setStatus(atividadeDTO.getStatus());
        atividade.setDataCriacao(LocalDateTime.now());
        Atividade atividadeSalva = atividadesRepository.save(atividade);
        return convertToDTO(atividadeSalva);
    }

    public List<AtividadeDTO> findAll() {
        return atividadesRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    public Optional<AtividadeDTO> findById(Long id) {
        return atividadesRepository.findById(id).map(this::convertToDTO);
    }

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

    public void delete(Long id) {
        if (atividadesRepository.existsById(id)) {
            atividadesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Atividade não encontrada com ID: " + id);
        }
    }

    private AtividadeDTO convertToDTO(Atividade atividade) {
        return new AtividadeDTO(
                atividade.getId(),
                atividade.getNome(),
                atividade.getDescricao(),
                atividade.getDataInicio(),
                atividade.getDataFim(),
                atividade.getStatus(),
                atividade.getProjeto().getId(),
                atividade.getUsuarioResponsavel().getId(),
                atividade.getDataCriacao()
        );
    }

    public List<AtividadeDTO> findByProjeto(Projeto id_projeto) {
        return atividadesRepository.findByProjeto(id_projeto)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<AtividadeDTO> findByUsuarioResponsavel(Long usuarioResponsavel) {
        
        Usuario usuario = new Usuario();
        usuario.setId(usuarioResponsavel);
        return atividadesRepository.findByUsuarioResponsavel(usuario)
                .stream()
                .map(this::convertToDTO)
                .toList();

    }

}
