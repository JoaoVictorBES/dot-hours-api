package br.com.system.dothours.model;

import java.time.LocalDateTime;

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
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private LocalDateTime data_registro;


    public LancamentoHoras() {

    }

    public LancamentoHoras(Long id, Atividade atividade, Usuario usuario, String descricao, LocalDateTime data_inicio, LocalDateTime data_fim, LocalDateTime data_registro) {
        this.id = id;
        this.atividade = atividade;
        this.usuario = usuario;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.data_registro = data_registro;
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

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    public LocalDateTime getData_registro() {
        return data_registro;
    }

    public void setData_registro(LocalDateTime data_registro) {
        this.data_registro = data_registro;
    }

}
