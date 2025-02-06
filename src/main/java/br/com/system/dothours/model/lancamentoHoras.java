package br.com.system.dothours.model;

import java.time.LocalDateTime;

import br.com.system.dothours.dto.LancamentoHorasDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lancamento_horas")
public class LancamentoHoras {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_atividade", nullable = false)
    private Atividade atividade; // Relação com Atividade

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private LocalDateTime dataRegistro;


    public LancamentoHoras() {

    }

    public LancamentoHoras(Long id, Atividade atividade, Usuario usuario, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDateTime dataRegistro) {
        this.id = id;
        this.atividade = atividade;
        this.usuario = usuario;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataRegistro = dataRegistro;
    }

    public static LancamentoHoras fromDTO(LancamentoHorasDTO dto) {
        LancamentoHoras lancamentoHoras = new LancamentoHoras();
        lancamentoHoras.setId(dto.getId());
        lancamentoHoras.setAtividade(dto.getAtividade());
        lancamentoHoras.setUsuario(dto.getUsuario());
        lancamentoHoras.setDescricao(dto.getDescricao());
        lancamentoHoras.setDataInicio(dto.getDataInicio());
        lancamentoHoras.setDataFim(dto.getDataFim());
        lancamentoHoras.setDataRegistro(dto.getDataRegistro());
        return lancamentoHoras;
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
