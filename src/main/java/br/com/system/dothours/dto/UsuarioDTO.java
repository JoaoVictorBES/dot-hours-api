package br.com.system.dothours.dto;

import java.time.LocalDateTime;

import br.com.system.dothours.model.Usuario;

public class UsuarioDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoLogin;
    private String role;

    public UsuarioDTO(){
        
    }

    public UsuarioDTO(Long id, String username, String password, String email, LocalDateTime dataCriacao, LocalDateTime ultimoLogin, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.ultimoLogin = ultimoLogin;
        this.role = role;
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getEmail(),
            usuario.getDataCriaçao(),
            usuario.getUltimoLogin(),
            usuario.getRole()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(LocalDateTime ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
}
