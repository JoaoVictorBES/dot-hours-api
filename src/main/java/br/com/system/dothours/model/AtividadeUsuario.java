package br.com.system.dothours.model;

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
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, referencedColumnName = "id")
    private Usuario Usuario;

    @ManyToOne
    @JoinColumn(name = "id_atividade", nullable = false, referencedColumnName = "id")
    private Atividade Atividade;
    private boolean ativo = false;

    public AtividadeUsuario() {
    }

    public AtividadeUsuario(Usuario Usuario, Atividade Atividade) {
        this.Usuario = Usuario;
        this.Atividade = Atividade;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
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
        return Atividade;
    }

    public void setAtividade(Atividade Atividade) {
        this.Atividade = Atividade;
    }


}
