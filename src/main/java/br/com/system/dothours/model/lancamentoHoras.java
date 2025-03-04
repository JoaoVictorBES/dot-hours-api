package br.com.system.dothours.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import br.com.system.dothours.dto.LancamentoHorasDTO;
import jakarta.persistence.Column;
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
    @Column(name = "id_lancamento")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_atividade", nullable = false)
    private Atividade atividade; // Relação com Atividade

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDateTime dataFim;

    @Column(name = "data_registro", nullable = false, updatable = false)
    private LocalDate dataRegistro;

    @Column(name = "tempo_duracao", nullable = false)
    private LocalTime tempoDuracao;


    public LancamentoHoras() {

    }

    

    public LancamentoHoras(Long id, Atividade atividade, Usuario usuario, String descricao, LocalDateTime dataInicio, LocalDateTime dataFim, LocalDate dataRegistro) {
        this.id = id;
        this.atividade = atividade;
        this.usuario = usuario;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataRegistro = dataRegistro;
    }

    public static LancamentoHoras fromReqDTO (LancamentoHorasDTO dto){
        Atividade atividade = new Atividade();
        Usuario usuario = new Usuario();
        LancamentoHoras lancamentoHoras = new LancamentoHoras();

        atividade.setId(dto.getIdAtividade());
        lancamentoHoras.setAtividade(atividade);

        usuario.setId(dto.getIdUsuario());
        lancamentoHoras.setUsuario(usuario);
        
        lancamentoHoras.setDescricao(dto.getDescricao());
        lancamentoHoras.setDataInicio(dto.getDataInicio());
        lancamentoHoras.setDataFim(dto.getDataFim());
        lancamentoHoras.setDataRegistro(dto.getDataRegistro());
        if (dto.getTempoDuracao() != null) {
            lancamentoHoras.setTempoDuracao(LocalTime.parse(dto.getTempoDuracao().toString()));
        }
        
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

    @Override
    public String toString() {
        return "LancamentoHoras{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", dataRegistro=" + dataRegistro +
                ", idAtividade=" + atividade +
                ", idUsuario=" + usuario +
                ", tempoDuracao=" + tempoDuracao +
                '}';
    }


}
