package br.com.system.dothours.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



/**
 * Configuração CORS (Cross-Origin Resource Sharing) para permitir que o frontend (na URL 
 * http://localhost:4200) acesse recursos do backend.
 * Esta configuração define as permissões de origem, métodos e cabeçalhos permitidos para 
 * as requisições CORS.
 * 
 * A configuração inclui dois componentes principais:
 * 1. {@link WebMvcConfigurer} para definir permissões de CORS para todos os endpoints.
 * 2. Um filtro {@link CorsFilter} registrado para fornecer uma configuração global de CORS.
 */
@Configuration
public class CorsConfig {


     /*
     * Define as permissões de CORS para as rotas da aplicação.
     * Este método configura a aplicação para aceitar requisições CORS apenas da origem 
     * "http://localhost:4200".
     *
     * @return A configuração CORS que será aplicada a todas as rotas.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("Authorization", "Content-Type", "Accept")
                    .allowCredentials(true)
                    .maxAge(3600);  // Cache de CORS por 1 hora
            }
        };
        
    }
    


     /**
     * Configura um filtro CORS personalizado para a aplicação.
     * Este método cria uma configuração de CORS com opções detalhadas, como permissões 
     * para credenciais, métodos, cabeçalhos e tempo de vida das permissões.
     * 
     * A configuração permite:
     * - Credenciais CORS.
     * - Cabeçalhos permitidos: Authorization, Content-Type, Accept.
     * - Métodos HTTP permitidos: GET, POST, PUT, DELETE.
     * - Um tempo máximo de validade de CORS de 1 hora (3600 segundos).
     * 
     * O filtro é registrado para ser aplicado a todas as rotas da aplicação.
     *
     * @return O filtro CORS configurado.
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        // Configurações de CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Configurações de CORS
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
        config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name()));
        config.setMaxAge(3600L);
        
        // Registra a configuração de CORS para todas as rotas
        source.registerCorsConfiguration("/**", config);
        
        // Cria o filtro de CORS e registra no Spring
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(-102);  // Defina a ordem de execução do filtro
        return bean;

    }

}
