package br.com.system.dothours.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadesRepository;

    
    public Atividade create(Atividade atividade) {

        atividade.setData_criacao(LocalDate.now()); 
        return atividadesRepository.save(atividade);

    }

    public List<Atividade> findAll() {

        return atividadesRepository.findAll();

    }

    public Optional<Atividade> findById(Long id) {

        return atividadesRepository.findById(id);

    }

    public Atividade update(Long id, Atividade atividadeAtualizada) {

        return atividadesRepository.findById(id).map(atividade -> {
            atividade.setId_projeto(atividadeAtualizada.getId_projeto());
            atividade.setNome(atividadeAtualizada.getNome());
            atividade.setDescricao(atividadeAtualizada.getDescricao());
            atividade.setData_inicio(atividadeAtualizada.getData_inicio());
            atividade.setData_fim(atividadeAtualizada.getData_fim());
            atividade.setStatus(atividadeAtualizada.getStatus());
            atividade.setId_usuario_responsavel(atividadeAtualizada.getId_usuario_responsavel());
            return atividadesRepository.save(atividade);
        }).orElseThrow(() -> new RuntimeException("Atividade não encontrada com ID: " + id));

    }

    public void delete(Long id) {

        if (atividadesRepository.existsById(id)) {
            atividadesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Atividade não encontrada com ID: " + id);
        }

    }

    public List<Atividade> findByProjeto(Long id_projeto) {

        return atividadesRepository.findByIdProjeto(id_projeto);

    }

    public List<Atividade> findByUsuarioResponsavel(Long id_usuario_responsavel) {

        return atividadesRepository.findByIdUsuarioResponsavel(id_usuario_responsavel);

    }

}
