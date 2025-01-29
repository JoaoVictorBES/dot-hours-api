package br.com.system.dothours.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.service.AtividadeService;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping
    public ResponseEntity<Atividade> create(@RequestBody Atividade atividade) {

        Atividade novaAtividade = atividadeService.create(atividade);
        return ResponseEntity.status(201).body(novaAtividade);

    }

    @GetMapping
    public ResponseEntity<List<Atividade>> findAll() {

        List<Atividade> atividades = atividadeService.findAll();
        return ResponseEntity.ok(atividades);
    
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Atividade> findById(@PathVariable Long id) {

        Optional<Atividade> atividade = atividadeService.findById(id);
        return atividade.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Atividade> update(@PathVariable Long id, @RequestBody Atividade atividadeAtualizada) {

        try {
            Atividade atividade = atividadeService.update(id, atividadeAtualizada);
            return ResponseEntity.ok(atividade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        try {
            atividadeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{id_projeto}")
    public ResponseEntity<List<Atividade>> findByProjeto(@PathVariable Long id_projeto) {

        List<Atividade> atividades = atividadeService.findByProjeto(id_projeto);
        return ResponseEntity.ok(atividades);

    }


    @GetMapping("/{id_usuario_responsavel}")
    public ResponseEntity<List<Atividade>> findByUsuarioResponsavel(@PathVariable Long id_usuario_responsavel) {
        List<Atividade> atividades = atividadeService.findByUsuarioResponsavel(id_usuario_responsavel);
        return ResponseEntity.ok(atividades);
    }

}
