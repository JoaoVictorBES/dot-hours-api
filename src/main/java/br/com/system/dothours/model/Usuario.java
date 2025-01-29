package br.com.system.dothours.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String nome;
    private String email;
    private String senha;
    private LocalDate data_criaçao;
    private LocalDate ultimo_login;
    private String role;

    public Usuario() {
        
    }

    public Usuario(Long id, String nome, String email, String senha, LocalDate data_criaçao, LocalDate ultimo_login, String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_criaçao = data_criaçao;
        this.ultimo_login = ultimo_login;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getData_criaçao() {
        return data_criaçao;
    }

    public void setData_criaçao(LocalDate data_criaçao) {
        this.data_criaçao = data_criaçao;
    }

    public LocalDate getUltimo_login() {
        return ultimo_login;
    }

    public void setUltimo_login(LocalDate ultimo_login) {
        this.ultimo_login = ultimo_login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

}
