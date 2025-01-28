package br.com.system.dothours.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lancamento_horas")
public class lancamento_horas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long id_atividade; // Chave estrangeira para atividade //
    private Long id_usuario; // Chave estrangeira para usuario //
    private String descricao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private LocalDateTime data_registro;


    public lancamento_horas() {

    }

    public lancamento_horas(Long id, Long id_atividade, Long id_usuario, String descricao, LocalDateTime data_inicio, LocalDateTime data_fim, LocalDateTime data_registro) {
        this.id = id;
        this.id_atividade = id_atividade;
        this.id_usuario = id_usuario;
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

    public Long getId_atividade() {
        return id_atividade;
    }

    public void setId_atividade(Long id_atividade) {
        this.id_atividade = id_atividade;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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
