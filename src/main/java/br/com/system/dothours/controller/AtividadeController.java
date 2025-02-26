package br.com.system.dothours.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.system.dothours.dto.AtividadeDTO;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;
import br.com.system.dothours.repository.UsuarioRepository;
import br.com.system.dothours.service.AtividadeService;
import br.com.system.dothours.service.UsuarioService;



/**
 * Controlador responsável pelas operações relacionadas às atividades.
 * Expondo endpoints REST para criação, leitura, atualização, exclusão e busca de atividades.
 * Utiliza o serviço {@link AtividadeService} para realizar a lógica de negócios.
 */
@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Cria uma nova atividade com os dados fornecidos.
     *
     * @param atividadeDTO DTO contendo os dados da nova atividade.
     * @return A resposta HTTP com o status 201 (Created) e o DTO da atividade recém-criada.
     *         Caso ocorra um erro ao criar a atividade, retorna o status 400 (Bad Request) com a mensagem de erro.
     */
    
    @PostMapping("/create")
    public ResponseEntity<?> criarAtividade(@RequestBody AtividadeDTO atividadeDTO) {
        try {
            System.out.println("Recebendo dados da atividade: " + atividadeDTO);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();

            System.out.println("Usuário autenticado: " + username);

            Usuario usuario = usuarioService.buscarPorUsername(username);       

            if (usuario == null || usuario.getRole() == null || !usuario.getRole().equals("ADMIN")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Apenas administradores podem criar atividades.");
            }

            AtividadeDTO novaAtividadeDTO = atividadeService.create(atividadeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAtividadeDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    /**
     * Busca todas as atividades cadastradas.
     *
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs das atividades encontradas.
     */
    @GetMapping("/listAll")
    public ResponseEntity<List<AtividadeDTO>> findAll() {

        List<AtividadeDTO> atividades = atividadeService.findAll();
        return ResponseEntity.ok(atividades);
    
    }



    /**
     * Busca uma atividade pelo ID.
     *
     * @param id O ID da atividade a ser buscada.
     * @return A resposta HTTP com o status 200 (OK) e o DTO da atividade encontrada.
     *         Caso a atividade não seja encontrada, retorna o status 404 (Not Found).
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<AtividadeDTO> findById(@PathVariable Long id) {

        return atividadeService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }



    /*
     * Atualiza uma atividade com os dados fornecidos.
     *
     * @param id O ID da atividade a ser atualizada.
     * @param atividadeAtualizada DTO contendo os dados atualizados da atividade.
     * @return A resposta HTTP com o status 200 (OK) e o DTO da atividade atualizada.
     *         Caso ocorra um erro ao atualizar a atividade, retorna o status 404 (Not Found).
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<AtividadeDTO> update(@PathVariable Long id, @RequestBody AtividadeDTO atividadeAtualizada) {

        try {
            AtividadeDTO atividade = atividadeService.update(id, atividadeAtualizada);
            return ResponseEntity.ok(atividade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }



    /**
     * Exclui uma atividade pelo ID.
     *
     * @param id O ID da atividade a ser excluída.
     * @return A resposta HTTP com o status 204 (No Content).
     *         Caso a atividade não seja encontrada, retorna o status 404 (Not Found).
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        try {
            atividadeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }



      /**
     * Retorna todas as atividades associadas a um projeto específico.
     *
     * @param idProjeto O projeto pelo qual as atividades serão filtradas.
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs das atividades associadas ao projeto.
     */
    @GetMapping("/findByProjeto/{id_projeto}")
    public ResponseEntity<List<AtividadeDTO>> findByProjeto(@PathVariable Projeto idProjeto) {

        List<AtividadeDTO> atividades = atividadeService.findByProjeto(idProjeto);
        return ResponseEntity.ok(atividades);

    }




      /**
     * Retorna todas as atividades associadas a um usuário responsável específico.
     *
     * @param usuarioResponsavel O ID do usuário responsável pelas atividades.
     * @return A resposta HTTP com o status 200 (OK) e a lista de DTOs das atividades atribuídas ao usuário.
     */
    @GetMapping("/findByUsuarioResponsavel/{usuarioResponsavel}")
    public ResponseEntity<List<AtividadeDTO>> findByUsuarioResponsavel(@PathVariable Long usuarioResponsavel) {

        List<AtividadeDTO> atividades = atividadeService.findByUsuarioResponsavel(usuarioResponsavel);
        return ResponseEntity.ok(atividades);

    }

}
