package br.com.system.dothours.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.service.ProjetoService;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    
    @PostMapping ("/ceate/projeto")
    public ResponseEntity<?> criarProjeto(@RequestBody Projeto projeto, @RequestParam Long usuarioId) {

        try {
            Projeto novoProjeto = projetoService.create(projeto, usuarioId);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProjeto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Projeto>> listarProjetos() {

        List<Projeto> projetos = projetoService.findAll();
        return ResponseEntity.ok(projetos);

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> buscarProjetoPorId(@PathVariable Long id) {

        Optional<Projeto> projeto = projetoService.findById(id);
        if (projeto.isPresent()) {
            return ResponseEntity.ok(projeto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado com ID: " + id);
        }

    }

    @PutMapping("/update/projeto/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody Projeto projetoAtualizado) {

        try {
            Projeto projetoAtualizadoResponse = projetoService.update(id, projetoAtualizado);
            return ResponseEntity.ok(projetoAtualizadoResponse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarProjeto(@PathVariable Long id) {

        try {
            projetoService.delete(id);
            return ResponseEntity.ok("Projeto deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}


