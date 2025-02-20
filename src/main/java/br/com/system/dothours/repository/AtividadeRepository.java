package br.com.system.dothours.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.Projeto;
import br.com.system.dothours.model.Usuario;

/*
 * Interface de repositório para a entidade {Atividade}. Estende {JpaRepository}
 * para fornecer operações de CRUD e consultas personalizadas para atividades associadas a projetos
 * e usuários responsáveis.
 * 
 * O repositório usa {JpaRepository} para acessar o banco de dados, oferecendo
 * funcionalidades como salvar, atualizar, excluir e listar atividades.
 * 
 * Essa interface também define métodos adicionais para filtrar atividades com base no projeto
 * e no usuário responsável, proporcionando uma consulta eficiente para esses casos específicos.
 * 
 * @see Atividade
 * @see Projeto
 * @see Usuario
 */
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

     /**
     * Encontra todas as atividades associadas a um determinado projeto.
     * 
     * @param projeto o projeto pelo qual as atividades serão filtradas
     * @return uma lista de {Atividade} associadas ao projeto especificado
     */
    List<Atividade> findByProjeto(Projeto projeto);


    /**
     * Encontra todas as atividades associadas a um determinado usuário responsável.
     * 
     * @param usuarioResponsavel o usuário responsável pelas atividades
     * @return uma lista de {Atividade} associadas ao usuário responsável especificado
     */
    List<Atividade> findByUsuarioResponsavel(Usuario usuarioResponsavel);

    @Query("SELECT a FROM Atividade a WHERE a.projeto.id = :idProjeto")
    List<Atividade> findByProjetoId(@Param("idProjeto") Long idProjeto);

}
