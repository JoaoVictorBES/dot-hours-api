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

import br.com.system.dothours.dto.ProjetoDTO;
import br.com.system.dothours.service.ProjetoService;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    
    @PostMapping ("/create")
    public ResponseEntity<?> criarProjeto(@RequestBody ProjetoDTO projetoDTO) {

        try {
            ProjetoDTO novoProjetoDTO = projetoService.create(projetoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProjetoDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/listAll")
    public ResponseEntity<List<ProjetoDTO>> listarProjetos() {

        List<ProjetoDTO> projetos = projetoService.findAll();
        return ResponseEntity.ok(projetos);

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProjetoDTO> buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        try {
            ProjetoDTO projetoAtualizado = projetoService.update(id, projetoDTO);
            return ResponseEntity.ok(projetoAtualizado);
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


