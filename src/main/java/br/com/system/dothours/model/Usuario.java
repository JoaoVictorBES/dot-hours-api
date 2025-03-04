package br.com.system.dothours.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.system.dothours.Enum.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    
   // private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id; 

    @Column(name = "nome_usuario", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "email_usuario", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "senha_usuario", nullable = false)
    private String password;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    @Enumerated(EnumType.STRING) 
    @Column(name = "role")
    private Role role;

    @Column(name = "recovery_token")
    private String recoveryToken; 

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    @ManyToMany
    @JoinTable(
        name = "atividades_usuarios",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_atividade")
    )
    private List<Atividade> atividades = new ArrayList<>();

    
    public Usuario() {
        
    }

    public Usuario(Long id, String username, String email, String password, LocalDateTime dataCriacao, LocalDateTime ultimoLogin, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dataCriacao = dataCriacao;
        this.ultimoLogin = ultimoLogin;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna as authorities com base na role
        return Collections.singletonList(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    public String getRecoveryToken() {
		return recoveryToken;
	}

	public void setRecoveryToken(String recoveryToken) {
		this.recoveryToken = recoveryToken;
	}

	public LocalDateTime getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(LocalDateTime tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}
}
