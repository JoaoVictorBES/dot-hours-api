package br.com.system.dothours.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.system.dothours.auth.Login.LoginRepository;


/*
 * Classe de configuração responsável por gerenciar a segurança da aplicação.
 * 
 * Esta classe configura o gerenciamento de autenticação e a codificação de senhas, permitindo que a aplicação valide as credenciais do usuário de forma segura.
 * Ela utiliza o Spring Security para fornecer suporte a autenticação e autorização, além de definir como as credenciais dos usuários são armazenadas e validadas.
 * 
 * Esta classe contém as seguintes configurações:
 * 
 *   Codificação de senha usando o algoritmo BCrypt.
 *   Configuração do provedor de autenticação com o repositório de login.
 *   Carregamento dos detalhes do usuário a partir do banco de dados via repositório.
 * 
 * @see PasswordEncoder
 * @see AuthenticationProvider
 * @see UserDetailsService
 */
@Configuration
public class SecurityManager {

    @Autowired
    private LoginRepository loginRepository;


    /**
     * Bean responsável por fornecer um codificador de senha.
     * Utiliza o algoritmo BCrypt para codificar as senhas de forma segura.
     * 
     * @return Um {@link PasswordEncoder} configurado para utilizar o algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    /**
     * Bean responsável por fornecer o provedor de autenticação.
     * Utiliza o {@link DaoAuthenticationProvider} para autenticar usuários a partir dos detalhes fornecidos pelo repositório de login.
     * 
     * @return Um {@link AuthenticationProvider} configurado com o serviço de detalhes do usuário e o codificador de senha.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }



     /**
     * Bean responsável por carregar os detalhes do usuário a partir do banco de dados.
     * O serviço de detalhes do usuário é utilizado pelo provedor de autenticação para validar as credenciais do usuário.
     * 
     * @return Um {@link UserDetailsService} que carrega o usuário a partir do repositório de login.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> loginRepository.findByNome(username)
            .map(usuario -> User.withUsername(usuario.getNome())
                .password(usuario.getSenha())
                .roles(usuario.getRole())
                .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

}
