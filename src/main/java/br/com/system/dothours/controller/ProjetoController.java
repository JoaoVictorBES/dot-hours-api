package br.com.system.dothours.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.Enum.PrioridadeProjeto;
import br.com.system.dothours.Enum.Role;
import br.com.system.dothours.Enum.StatusProjeto;
import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.dto.ProjetoDTO;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.service.ProjetoService;
import br.com.system.dothours.service.UsuarioService;


/**
 * Controlador responsável pelas operações relacionadas ao gerenciamento de projetos.
 * Expondo endpoints REST para criação, leitura, atualização e exclusão de projetos.
 * Utiliza o serviço {@link ProjetoService} para realizar a lógica de negócios.
 */
@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private UsuarioService usuarioService;



    @PostMapping ("/create")
    public ResponseEntity<?> criarProjeto(@RequestBody ProjetoDTO projetoDTO) {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();  
        
            Usuario usuario = usuarioService.buscarPorUsername(username); 

            if (!usuario.getRole().equals(Role.ADMIN)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas administradores podem criar projetos.");
            }
            projetoDTO.setIdUsuarioResponsavel(usuario.getId());

            ProjetoDTO novoProjetoDTO = projetoService.create(projetoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProjetoDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }



    /**
     * Lista todos os projetos cadastrados.
     *
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs dos projetos cadastrados.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<ProjetoDTO>> listarProjetos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProjetoDTO> projetosDTOPage = projetoService.findAll(pageable);

        return ResponseEntity.ok(projetosDTOPage);
    }

    /**
     * Busca um projeto pelo ID.
     *
     * @param id ID do projeto a ser buscado.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do projeto encontrado.
     *         Caso o projeto não seja encontrado, retorna o status 404 (Not Found).
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<ProjetoDTO> buscarProjetoPorId(@PathVariable Long id) {
        return projetoService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



    /*
     * Atualiza um projeto com os dados fornecidos.
     *
     * @param id ID do projeto a ser atualizado.
     * @param projetoDTO DTO contendo os dados atualizados do projeto.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do projeto atualizado.
     *         Caso ocorra um erro ao atualizar o projeto, retorna o status 404 (Not Found) com a mensagem de erro.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody ProjetoDTO projetoDTO) {
        try {
            ProjetoDTO projetoAtualizado = projetoService.update(id, projetoDTO);
            return ResponseEntity.ok(projetoAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    
    /**
     * Deleta um projeto pelo ID.
     *
     * @param id ID do projeto a ser deletado.
     * @return A resposta HTTP com o status 200 (OK) e a mensagem de sucesso.
     *         Caso o projeto não seja encontrado, retorna o status 404 (Not Found) com a mensagem de erro.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarProjeto(@PathVariable Long id) {

        try {
            projetoService.delete(id);
            return ResponseEntity.ok("Projeto deletado com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProjetoDTO>> filtrarProjetos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) StatusProjeto status,
            @RequestParam(required = false) PrioridadeProjeto prioridade,
            @RequestParam(required = false) LocalDate dataInicio) {

        List<ProjetoDTO> projetos = projetoService.findByFilters(nome, status, prioridade, dataInicio);
        return ResponseEntity.ok(projetos);
    }

}


