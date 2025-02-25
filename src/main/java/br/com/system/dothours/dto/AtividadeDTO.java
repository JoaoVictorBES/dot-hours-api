package br.com.system.dothours.dto;

import java.time.LocalDate;
import java.util.List;

public class AtividadeDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private Long idUsuarioResponsavel;
    private LocalDate dataCriacao; 
    private List<UsuarioDTO> usuarioVinculado;
    private List<ProjetoDTO> projetoVinculado;
    private Long idProjetoVinculado;
    private Long idUsuarioVinculado;

    // Construtor padrão
    public AtividadeDTO() {}

    // Construtor com parâmetros
    public AtividadeDTO(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, 
                         String status, Long idUsuarioResponsavel, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
        this.dataCriacao = dataCriacao;
    }

    // Getters e setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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


    public List<ProjetoDTO> getProjetoVinculado() {
        return projetoVinculado;
    }

    public void setProjetoVinculado(List<ProjetoDTO> projetoVinculado) {
        this.projetoVinculado = projetoVinculado;
    }

    public List<UsuarioDTO> getUsuarioVinculado() {
        return usuarioVinculado;
    }

    public void setUsuarioVinculado(List<UsuarioDTO> usuarioVinculado) {
        this.usuarioVinculado = usuarioVinculado;
    }

    public Long getIdProjetoVinculado() {
        return idProjetoVinculado;
    }

    public void setIdProjetoVinculado(Long idProjetoVinculado) {
        this.idProjetoVinculado = idProjetoVinculado;
    }

    public Long getIdUsuarioVinculado() {
        return idUsuarioVinculado;
    }

    public void setIdUsuarioVinculado(Long idUsuarioVinculado) {
        this.idUsuarioVinculado = idUsuarioVinculado;
    }

}
