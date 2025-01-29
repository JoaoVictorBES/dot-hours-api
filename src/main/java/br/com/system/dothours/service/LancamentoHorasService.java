package br.com.system.dothours.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.repository.LancamentoHorasRepository;

@Service
public class LancamentoHorasService {

    @Autowired
    private LancamentoHorasRepository lancamentoHorasRepository;

    
    public LancamentoHoras create(LancamentoHoras lancamentoHoras) {

        lancamentoHoras.setData_registro(LocalDateTime.now()); 
        return lancamentoHorasRepository.save(lancamentoHoras);

    }

    
    public List<LancamentoHoras> findAll() {

        return lancamentoHorasRepository.findAll();

    }

    
    public Optional<LancamentoHoras> findById(Long id) {

        return lancamentoHorasRepository.findById(id);

    }

    
    public LancamentoHoras update(Long id, LancamentoHoras lancamentoAtualizado) {

        return lancamentoHorasRepository.findById(id).map(lancamento -> {
            lancamento.setId_atividade(lancamentoAtualizado.getId_atividade());
            lancamento.setId_usuario(lancamentoAtualizado.getId_usuario());
            lancamento.setDescricao(lancamentoAtualizado.getDescricao());
            lancamento.setData_inicio(lancamentoAtualizado.getData_inicio());
            lancamento.setData_fim(lancamentoAtualizado.getData_fim());
            return lancamentoHorasRepository.save(lancamento);
        }).orElseThrow(() -> new RuntimeException("Lançamento de horas não encontrado com ID: " + id));

    }

    
    public void delete(Long id) {

        if (lancamentoHorasRepository.existsById(id)) {
            lancamentoHorasRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lançamento de horas não encontrado com ID: " + id);
        }

    }

}
