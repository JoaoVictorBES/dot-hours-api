package br.com.system.dothours.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projetos")
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate  data_inicio;
    private LocalDate data_fim;
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_usuario_responsavel", referencedColumnName = "id", nullable = false)
    private Usuario usuarioResponsavel; 

    private LocalDate data_criacao;
    private String prioridade;

    public Projeto() {

    }

    public Projeto(Long id, String nome, String descricao, LocalDate data_inicio, LocalDate data_fim, Usuario id_usuario_responsavel, LocalDate data_criacao, String prioridade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.usuarioResponsavel = id_usuario_responsavel;
        this.data_criacao = data_criacao;
        this.prioridade = prioridade;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public Long getId_usuario_responsavel() {
        return usuarioResponsavel != null ? usuarioResponsavel.getId() : null;
    }

     public void setId_usuario_responsavel(Long id_usuario_responsavel) {
        if (id_usuario_responsavel != null) {
            this.usuarioResponsavel = new Usuario();
            this.usuarioResponsavel.setId(id_usuario_responsavel);
        }
    }

    public LocalDate getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(LocalDate data_criacao) {
        this.data_criacao = data_criacao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

}
