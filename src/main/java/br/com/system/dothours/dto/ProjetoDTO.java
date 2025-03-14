package br.com.system.dothours.dto;

import java.time.LocalDate;
import java.util.List;

import br.com.system.dothours.Enum.PrioridadeProjeto;
import br.com.system.dothours.Enum.StatusProjeto;
import br.com.system.dothours.model.Projeto;




public class ProjetoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusProjeto status;
    private Long idUsuarioResponsavel;
    private String nomeUsuarioResponsavel;
    private LocalDate dataCriacao;
    private PrioridadeProjeto prioridade;
    private List<AtividadeDTO> atividades;

    public ProjetoDTO() {}

    public ProjetoDTO(Projeto projeto) {
        this.id = projeto.getId();
        this.nome = projeto.getNome();
        this.descricao = projeto.getDescricao();
    }

    public ProjetoDTO(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, 
                      StatusProjeto status, Long idUsuarioResponsavel, String nomeUsuarioResponsavel, LocalDate dataCriacao, PrioridadeProjeto prioridade, 
                      List<AtividadeDTO> atividades) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
        this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
        this.atividades = atividades;
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

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public Long getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public void setIdUsuarioResponsavel(Long idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public PrioridadeProjeto getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeProjeto prioridade) {
        this.prioridade = prioridade;
    }

    public List<AtividadeDTO> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeDTO> atividades) {
        this.atividades = atividades;
    }

    public String getNomeUsuarioResponsavel() {
        return nomeUsuarioResponsavel;
    }

    public void setNomeUsuarioResponsavel(String nomeUsuarioResponsavel) {
        this.nomeUsuarioResponsavel = nomeUsuarioResponsavel;
    }

    

    /*public static ProjetoDTO fromEntity(Projeto projeto) {
        return new ProjetoDTO(projeto.getId(), projeto.getNome(), projeto.getDescricao()); // Ajuste conforme seu modelo
    }*/

 
    

}
