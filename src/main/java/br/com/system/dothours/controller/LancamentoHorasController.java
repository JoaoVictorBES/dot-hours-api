package br.com.system.dothours.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.system.dothours.Enum.Role;

import br.com.system.dothours.dto.LancamentoHorasDTO;
import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.service.LancamentoHorasService;



/**
 * Controlador responsável pelas operações relacionadas ao gerenciamento de lançamentos de horas.
 * Expondo endpoints REST para criação, leitura, atualização e exclusão de lançamentos de horas.
 * Utiliza o serviço {@link LancamentoHorasService} para realizar a lógica de negócios.
 */
@RestController
@RequestMapping("/api/lancar-horas")
public class LancamentoHorasController {

    @Autowired
    private LancamentoHorasService lancamentoHorasService;


     /**
     * Cria um novo lançamento de horas com os dados fornecidos.
     *
     * @param lancamentoHorasDTO DTO contendo os dados do novo lançamento de horas.
     * @return A resposta HTTP com o status 201 (Created) e o DTO do lançamento de horas recém-criado.
     *         Caso ocorra um erro ao criar o lançamento, retorna o status 400 (Bad Request) com a mensagem de erro.
     */
    @PostMapping("/create")
    public ResponseEntity<LancamentoHorasDTO> criarLancamento(@RequestBody LancamentoHorasDTO lancamentoHorasDTO, Authentication authentication) {
        System.out.println("Recebendo lançamento de horas: " + lancamentoHorasDTO);
        System.out.println("Usuário autenticado: " + authentication.getName());
                
                try {
                    // Convertendo DTO para a entidade
                    LancamentoHoras lancamentoHoras = LancamentoHoras.fromReqDTO(lancamentoHorasDTO);
                    System.out.println("🔄 Convertido para entidade: " + lancamentoHoras);
        
                    // Criando o lançamento no serviço
                    LancamentoHoras novoLancamento = lancamentoHorasService.create(lancamentoHoras);
                    System.out.println("✅ Lançamento salvo: " + novoLancamento);
        
                    // Convertendo a entidade de volta para DTO para a resposta
                    LancamentoHorasDTO novoLancamentoDTO = LancamentoHorasDTO.fromEntity(novoLancamento);
                    //novoLancamentoDTO.getUsuario().setRole(Role.ADMIN);
                    return ResponseEntity.status(HttpStatus.CREATED).body(novoLancamentoDTO);
        
                } catch (RuntimeException e) {
        
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LancamentoHorasDTO(e.getMessage()));
        
                }
            }
        
        
            /**
     * Lista todos os lançamentos de horas cadastrados.
     *
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs dos lançamentos de horas cadastrados.
     */
    @GetMapping ("/findAll")
    public ResponseEntity<List<LancamentoHorasDTO>> listarLancamentos() {

        List<LancamentoHoras> lancamentos = lancamentoHorasService.findAll();
        List<LancamentoHorasDTO> lancamentosDTO = lancamentos.stream()
            .map(LancamentoHorasDTO::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(lancamentosDTO);

    }

     /**
     * Retorna um lançamento de horas específico pelo seu ID.
     *
     * @param id O ID do lançamento de horas a ser recuperado.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do lançamento de horas encontrado.
     *         Caso o lançamento não seja encontrado, retorna o status 404 (Not Found) com a mensagem de erro.
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<?> buscarLancamentoPorId(@PathVariable Long id) {

        Optional<LancamentoHoras> lancamento = lancamentoHorasService.findById(id);
        return lancamento.map(l -> ResponseEntity.ok(LancamentoHorasDTO.fromEntity(l)))
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                        .body(new LancamentoHorasDTO("Lançamento de horas não encontrado com ID: " + id)));

    }




    /*
     * Atualiza um lançamento de horas com os dados fornecidos.
     *
     * @param id O ID do lançamento de horas a ser atualizado.
     * @param lancamentoAtualizadoDTO DTO contendo os dados atualizados do lançamento de horas.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do lançamento de horas atualizado.
     *         Caso ocorra um erro ao atualizar o lançamento, retorna o status 404 (Not Found) com a mensagem de erro.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizarLancamento(@PathVariable Long id, @RequestBody LancamentoHorasDTO lancamentoAtualizadoDTO) {

        try {
            LancamentoHoras lancamentoAtualizado = LancamentoHoras.fromReqDTO(lancamentoAtualizadoDTO);
            LancamentoHoras lancamento = lancamentoHorasService.update(id, lancamentoAtualizado);
            return ResponseEntity.ok(LancamentoHorasDTO.fromEntity(lancamento));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LancamentoHorasDTO(e.getMessage()));
        }

    }



    /**
     * Deleta um lançamento de horas pelo ID.
     *
     * @param id O ID do lançamento de horas a ser deletado.
     * @return A resposta HTTP com o status 200 (OK) caso o lançamento seja deletado, ou 404 (Not Found) caso não exista.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarLancamento(@PathVariable Long id) {

        try {
            lancamentoHorasService.delete(id);
            return ResponseEntity.ok("Lançamento de horas deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/search")
    public ResponseEntity<List<LancamentoHorasDTO>> buscarLancamentos(@RequestParam(required = false) Long idUsuario, @RequestParam(required = false) String nomeAtividade) {

        List<LancamentoHoras> lancamentos = lancamentoHorasService.buscarLancamentos(idUsuario, nomeAtividade);
        
        List<LancamentoHorasDTO> lancamentosDTO = lancamentos.stream()
            .map(LancamentoHorasDTO::fromEntity)
            .collect(Collectors.toList());
    
    return ResponseEntity.ok(lancamentosDTO);
}


}
