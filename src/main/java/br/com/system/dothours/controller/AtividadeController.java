package br.com.system.dothours.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.service.AtividadeService;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

     @PostMapping("/create")
    public ResponseEntity<?> criarAtividade(@RequestBody AtividadeDTO atividadeDTO) {
        try {
            AtividadeDTO novaAtividade = atividadeService.create(atividadeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAtividade);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AtividadeDTO>> findAll() {

        List<AtividadeDTO> atividades = atividadeService.findAll();
        return ResponseEntity.ok(atividades);
    
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<AtividadeDTO> findById(@PathVariable Long id) {

        return atividadeService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AtividadeDTO> update(@PathVariable Long id, @RequestBody AtividadeDTO atividadeAtualizada) {

        try {
            AtividadeDTO atividade = atividadeService.update(id, atividadeAtualizada);
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

    @GetMapping("/findByProjeto/{id_projeto}")
    public ResponseEntity<List<AtividadeDTO>> findByProjeto(@PathVariable Projeto idProjeto) {

        List<AtividadeDTO> atividades = atividadeService.findByProjeto(idProjeto);
        return ResponseEntity.ok(atividades);

    }


    @GetMapping("/findByUsuarioResponsavel/{usuarioResponsavel}")
    public ResponseEntity<List<AtividadeDTO>> findByUsuarioResponsavel(@PathVariable Long usuarioResponsavel) {

        List<AtividadeDTO> atividades = atividadeService.findByUsuarioResponsavel(usuarioResponsavel);
        return ResponseEntity.ok(atividades);

    }

}
