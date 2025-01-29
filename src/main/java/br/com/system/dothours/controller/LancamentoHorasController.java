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
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.service.LancamentoHorasService;

@RestController
@RequestMapping("/api/lancamento-horas")
public class LancamentoHorasController {

    @Autowired
    private LancamentoHorasService lancamentoHorasService;

    @PostMapping("/criar")
    public ResponseEntity<?> criarLancamento(@RequestBody LancamentoHoras lancamentoHoras) {

        try {
            LancamentoHoras novoLancamento = lancamentoHorasService.create(lancamentoHoras);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamento);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping ("/listAll")
    public ResponseEntity<List<LancamentoHoras>> listarLancamentos() {

        List<LancamentoHoras> lancamentos = lancamentoHorasService.findAll();
        return ResponseEntity.ok(lancamentos);

    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> buscarLancamentoPorId(@PathVariable Long id) {

        Optional<LancamentoHoras> lancamento = lancamentoHorasService.findById(id);
        if (lancamento.isPresent()) {
            return ResponseEntity.ok(lancamento.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Lançamento de horas não encontrado com ID: " + id);
        }

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizarLancamento(@PathVariable Long id, @RequestBody LancamentoHoras lancamentoAtualizado) {

        try {
            LancamentoHoras atualizado = lancamentoHorasService.update(id, lancamentoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarLancamento(@PathVariable Long id) {

        try {
            lancamentoHorasService.delete(id);
            return ResponseEntity.ok("Lançamento de horas deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

}
