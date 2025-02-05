package br.com.system.dothours.dto;

import java.time.LocalDate;

import br.com.system.dothours.model.Usuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataCriacao;
    private LocalDate ultimoLogin;
    private String role;

    public UsuarioDTO(Long id, String nome, String email, LocalDate dataCriacao, LocalDate ultimoLogin, String role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.ultimoLogin = ultimoLogin;
        this.role = role;
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getData_criaçao(),
            usuario.getUltimo_login(),
            usuario.getRole()
        );
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

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDate ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    
}
