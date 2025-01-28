package br.com.system.dothours.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividades")
public class atividades {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_projeto; // Inlcuir chave estrangeira para projetos//
    private String nome;
    private String descricao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private String status;
    private Long id_usuario_responsavel; // Incluir chave estrangeira para usuários//
    private LocalDate data_criacao;


    public atividades() {
        
    }

    public atividades(Long id, Long id_projeto, String nome, String descricao, LocalDateTime data_inicio, LocalDateTime data_fim, String status, Long id_usuario_responsavel, LocalDate data_criacao) {
        this.id = id;
        this.id_projeto = id_projeto;
        this.nome = nome;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.status = status;
        this.id_usuario_responsavel = id_usuario_responsavel;
        this.data_criacao = data_criacao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(Long id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId_usuario_responsavel() {
        return id_usuario_responsavel;
    }

    public void setId_usuario_responsavel(Long id_usuario_responsavel) {
        this.id_usuario_responsavel = id_usuario_responsavel;
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    
}
