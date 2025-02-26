package br.com.system.dothours.model;

import java.time.LocalDate;
import java.util.List;

import br.com.system.dothours.Enum.PrioridadeProjeto;
import br.com.system.dothours.Enum.StatusProjeto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "projetos")
public class Projeto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long id;
    
    @Column(name = "nome_projeto", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao_projeto", nullable = false)
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Enumerated(EnumType.ORDINAL) 
    @Column(name = "status_projeto", nullable = false)
    private StatusProjeto status;

    @ManyToOne
    @JoinColumn(name = "id_usuario_responsavel", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuarioResponsavel; 

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING) 
    @Column(name = "prioridade_projeto", nullable = false)
    private PrioridadeProjeto prioridade;

    @OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Atividade> atividades;

    public Projeto() {}

    public Projeto(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim, 
                   Usuario usuarioResponsavel, LocalDate dataCriacao, PrioridadeProjeto prioridade, StatusProjeto status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public PrioridadeProjeto getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeProjeto prioridade) {
        this.prioridade = prioridade;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }
    

}
