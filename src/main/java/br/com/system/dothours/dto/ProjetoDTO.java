package br.com.system.dothours.dto;

import java.time.LocalDate;

public class ProjetoDTO {

    private String nome;
    private String descricao;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private String status;
    private Long id_usuario_responsavel;  
    private LocalDate data_criacao;
    private String prioridade;
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
    public String getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    

}
