package br.com.system.dothours.model;


import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividades")
public class Atividade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false, referencedColumnName = "id_projeto")
    private Projeto projeto;

    @Column(name = "nome_atividade", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao_atividade", nullable = false)
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "status_atividade", nullable = false, length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_usuario_responsavel", nullable = false, referencedColumnName = "id_usuario")
    private Usuario usuarioResponsavel;

    @Column(name = "data_criacao", nullable = false)
    private LocalDate dataCriacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDate.now(); // Define automaticamente a data de criação
    }

    public Atividade() {
        
    }

    public Atividade(Long id, Projeto projeto, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, String status, Usuario usuarioResponsavel, LocalDate dataCriacao) {
        this.id = id;
        this.projeto = projeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
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



    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    
}
