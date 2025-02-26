package br.com.system.dothours.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "atividades_usuarios")
public class AtividadeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_atividade_usuario")
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_atividade", nullable = false, referencedColumnName = "id_atividade")
    private Atividade atividade;
    
    private boolean ativo = false;

    public AtividadeUsuario() {
    }

    public AtividadeUsuario(Usuario usuario, Atividade atividade) {
        this.usuario = usuario;
        this.atividade = atividade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
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


}
