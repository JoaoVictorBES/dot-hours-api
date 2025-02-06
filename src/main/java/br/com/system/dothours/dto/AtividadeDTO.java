package br.com.system.dothours.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AtividadeDTO {

    public AtividadeDTO(Long id1, String nome1, String descricao1, LocalDateTime dataInicio1, LocalDateTime dataFim1, String status1, Long id2, Long id3, LocalDateTime dataCriacao1) {
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
    // Getters e Setters
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
    public void setPrioridade(Object prioridade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrioridade'");
    }
}


