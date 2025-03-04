package br.com.system.dothours.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.system.dothours.model.Atividade;
import br.com.system.dothours.model.LancamentoHoras;
import br.com.system.dothours.model.Usuario;

public class LancamentoHorasDTO {

    private Long id;
    private Long idAtividade;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AtividadeDTO atividade; 
    private Long idUsuario;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UsuarioDTO usuario;
    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInicio;
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataFim;
    private LocalDate dataRegistro;

    @JsonFormat(pattern = "HH:mm") 
    private LocalTime tempoDuracao;

    public static LancamentoHorasDTO fromEntity(LancamentoHoras lancamentoHoras) {
        LancamentoHorasDTO dto = new LancamentoHorasDTO();
        dto.setId(lancamentoHoras.getId());
        
        // Convertendo as entidades para seus respectivos IDs
        dto.setIdAtividade(lancamentoHoras.getAtividade().getId());
        dto.setIdUsuario(lancamentoHoras.getUsuario().getId());
        
        // Outros atributos
        AtividadeDTO atividadeDTO = new AtividadeDTO(lancamentoHoras.getAtividade().getId());

        UsuarioDTO usuarioDTO = new UsuarioDTO(null, null, null, null, null, null);

        dto.setAtividadeDTO(atividadeDTO);
        dto.setUsuarioDTO(usuarioDTO);
        dto.setDescricao(lancamentoHoras.getDescricao());
        dto.setDataInicio(lancamentoHoras.getDataInicio());
        dto.setDataFim(lancamentoHoras.getDataFim());
        dto.setDataRegistro(lancamentoHoras.getDataRegistro());
        dto.setTempoDuracao(lancamentoHoras.getTempoDuracao());
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
    public AtividadeDTO getAtividade() {
        return atividade;
    }
    public void setAtividadeDTO(AtividadeDTO atividade) {
        this.atividade = atividade;
    }
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    public void setUsuarioDTO(UsuarioDTO usuario) {
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
    public LocalDate getDataRegistro() {
        return dataRegistro;
    }
    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalTime getTempoDuracao() {
        return tempoDuracao;
    }

    public void setTempoDuracao(LocalTime tempoDuracao) {
        this.tempoDuracao = tempoDuracao;
    }

    public Long getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Long idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "LancamentoHorasDTO{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", dataRegistro='" + dataRegistro + '\'' +
                ", idAtividade=" + idAtividade +
                ", idUsuario=" + idUsuario +
                ", tempoDuracao='" + tempoDuracao + '\'' +
                '}';
    }


}
