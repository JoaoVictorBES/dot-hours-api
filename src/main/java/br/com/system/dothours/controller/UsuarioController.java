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

import br.com.system.dothours.dto.UsuarioCriadoDTO;
import br.com.system.dothours.dto.UsuarioDTO;
import br.com.system.dothours.service.UsuarioService;


/*
 * Controlador responsável pelas operações relacionadas ao gerenciamento de usuários.
 * Expondo endpoints REST para criação, leitura, atualização e exclusão de usuários.
 * Utiliza o serviço {@link UsuarioService} para realizar a lógica de negócios.
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;



     /**
     * Cria um novo usuário com os dados fornecidos.
     *
     * @param usuarioCreateDTO DTO contendo os dados do novo usuário a ser criado.
     * @return A resposta HTTP com o status 201 (Created) e o DTO do usuário recém-criado.
     */
    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioCriadoDTO usuarioCreateDTO) {
        UsuarioDTO novoUsuario = usuarioService.create(usuarioCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }



    /**
     * Busca todos os usuários cadastrados.
     *
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs de usuários.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
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
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UsuarioCriadoDTO usuarioCreateDTO) {
        try {
            UsuarioDTO usuarioAtualizado = usuarioService.update(id, usuarioCreateDTO);
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
}
