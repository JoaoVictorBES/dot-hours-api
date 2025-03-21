package br.com.system.dothours.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.com.system.dothours.Enum.Role;
import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.dto.UsuarioDTO;
import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.service.UsuarioService;
import io.swagger.models.Response;


/*
 * Controlador responsável pelas operações relacionadas ao gerenciamento de usuários.
 * Expondo endpoints REST para criação, leitura, atualização e exclusão de usuários.
 * Utiliza o serviço {UsuarioService} para realizar a lógica de negócios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    /**
     * Endpoint para cadastrar um novo usuário no sistema.
     *
     * Este método recebe um objeto {Usuario} no corpo da requisição, chama o serviço para criar
     * o usuário e retorna um {UsuarioDTO} com os dados do usuário salvo.
     *
     * Em caso de erro durante o processo de criação, uma resposta com status {500 Internal Server Error}
     * é retornada.
     *
     * @param usuario O objeto {Usuario} contendo os dados do usuário a ser cadastrado.
     * @return Um {ResponseEntity} contendo o {UsuarioDTO} do usuário salvo e o status {201 Created}
     * em caso de sucesso, ou {500 Internal Server Error} em caso de falha.
     */
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioDTO> create(@RequestBody Usuario usuario) {
        try {
            UsuarioDTO usuarioSalvo = usuarioService.create(usuario);

            System.out.println("Usuário salvo: " + usuarioSalvo);

            return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /**
     * Busca todos os usuários cadastrados.
     *
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs de usuários.
     */
    @GetMapping("/findAll")
    public ResponseEntity<Page<UsuarioDTO>> findAll( 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {

        Pageable pageable = PageRequest.of(page, size);
        
        Page<UsuarioDTO> usuarioDTOPage = usuarioService.findAll(pageable);

        return ResponseEntity.ok(usuarioDTOPage);
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<UsuarioDTO>> listAll() {
        return ResponseEntity.ok(usuarioService.listAll());
    }



    /**
     * Busca um usuário pelo ID.
     *
     * @param id O ID do usuário a ser buscado.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do usuário encontrado, ou 404 (Not Found) caso não exista.
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                             .map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }



    /**
     * Atualiza um usuário com os dados fornecidos.
     *
     * @param id O ID do usuário a ser atualizado.
     * @param usuarioCreateDTO DTO contendo os dados do usuário a ser atualizado.
     * @return A resposta HTTP com o status 200 (OK) e o DTO do usuário atualizado, ou 404 (Not Found) caso não exista.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioDTO);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }



    /**
     * Exclui um usuário pelo ID.
     *
     * @param id O ID do usuário a ser excluído.
     * @return A resposta HTTP com o status 204 (No Content) caso o usuário seja excluído, ou 404 (Not Found) caso não exista.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

    @GetMapping("/findByFilters")
    public ResponseEntity<List<UsuarioDTO>> findByFilters(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) Role role,
        @RequestParam(required = false) Atividade atividade,
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ultimoLogin) {

        List<UsuarioDTO> usuarios = usuarioService.findByFilters(nome, role, atividade, ultimoLogin);
        
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}/atividades")
    public ResponseEntity<List<AtividadeDTO>> listarAtividadesPorUsuario(@PathVariable Long id) {
        List<AtividadeDTO> atividades = usuarioService.listarAtividadesPorUsuario(id);
        return ResponseEntity.ok(atividades);
    }

}
