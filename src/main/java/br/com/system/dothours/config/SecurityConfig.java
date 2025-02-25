package br.com.system.dothours.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/*
 * Configuração de segurança do Spring Security para a aplicação.
 * 
 * A classe {SecurityConfig} configura as regras de segurança, incluindo autenticação e autorização, para a aplicação.
 * Ela define um filtro personalizado de autenticação JWT, um provedor de autenticação e configura a proteção das rotas da aplicação.
 * O objetivo é garantir que a aplicação utilize segurança baseada em JWT para autenticação e que o acesso a certas rotas seja restrito conforme necessário.
 * 
 * As configurações de segurança incluem:
 * 
 *   Desabilitação da proteção CSRF, já que a aplicação utiliza autenticação baseada em tokens.
 *   Desabilitação da configuração de CORS por padrão no Spring Security.
 *   Configuração para permitir todas as requisições sem restrições de autenticação.
 *   Adição de um filtro de autenticação JWT para interceptar as requisições.
 *   Configuração para garantir que as sessões não sejam mantidas (stateless).
 * 
 * 
 * @see JwtAuthenticationFilter
 * @see AuthenticationProvider
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
    private JwtAuthenticationFilter jwtAuthFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;



     /**
     * Configura a cadeia de filtros de segurança, incluindo a desabilitação da proteção CSRF,
     * desabilitação da configuração CORS, permissões de requisições, filtro JWT e política de sessão.
     * 
     * @param http A configuração de segurança do Spring Security.
     * @return A configuração de segurança construida.
     * @throws Exception Se ocorrer um erro ao configurar a segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())  
        .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/login").permitAll()  // Permite login e cadastro
            .requestMatchers(HttpMethod.GET,  "/api/projetos/**").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/api/projetos/**").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/atividades/**").permitAll()
            .requestMatchers(HttpMethod.POST,  "/api/atividades/**").permitAll()
            .requestMatchers(HttpMethod.GET,  "/api/usuarios/**").permitAll()
            .requestMatchers(HttpMethod.POST,  "/api/usuarios/**").permitAll()
            .requestMatchers(HttpMethod.POST,  "/api/atividades/**").permitAll()
            .anyRequest().authenticated()  // Requer autenticação para outras rotas
        )
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    

        return http.build();
    }

    
}
