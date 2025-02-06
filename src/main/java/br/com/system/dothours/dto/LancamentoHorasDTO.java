package br.com.system.dothours.dto;

import java.time.LocalDateTime;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.model.Usuario;

public class LancamentoHorasDTO {

   
    private Long id;
    private Atividade atividade; 
    private Usuario usuario;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataRegistro;

    public static LancamentoHorasDTO fromEntity(LancamentoHoras lancamentoHoras) {
        LancamentoHorasDTO dto = new LancamentoHorasDTO();
        dto.setId(lancamentoHoras.getId());
        dto.setAtividade(lancamentoHoras.getAtividade());
        dto.setUsuario(lancamentoHoras.getUsuario());
        dto.setDescricao(lancamentoHoras.getDescricao());
        dto.setDataInicio(lancamentoHoras.getDataInicio());
        dto.setDataFim(lancamentoHoras.getDataFim());
        dto.setDataRegistro(lancamentoHoras.getDataRegistro());
        return dto;
    }

    public LancamentoHorasDTO() {
    }

    public LancamentoHorasDTO(String errorMessage) {
        this.descricao = errorMessage;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Atividade getAtividade() {
        return atividade;
    }
    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    

}
