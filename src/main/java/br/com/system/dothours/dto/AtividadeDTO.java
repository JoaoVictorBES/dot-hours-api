package br.com.system.dothours.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AtividadeDTO {

    public AtividadeDTO(Long idAtividade, String nomeAtividade, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim, String status, Long idProjeto, Long idUsuarioResponsavel, LocalDateTime dataCriacao) {
    }

    
    private Long id;
    private Long idProjeto;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String status;
    private Long idUsuarioResponsavel;
    private LocalDate dataCriacao;

    
    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public Long getIdProjeto() { 
        return idProjeto; 
    }

    public void setIdProjeto(Long idProjeto) { 
        this.idProjeto = idProjeto; 
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

    public LocalDateTime getDataInicio() { 
        return dataInicio; 
    }

    public void setDataInicio(LocalDateTime dataInicio) { 
        this.dataInicio = dataInicio; 
    }

    public LocalDateTime getDataFim() {
        return dataFim; 
    }

    public void setDataFim(LocalDateTime dataFim) {
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

}


