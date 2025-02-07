package br.com.system.dothours.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Classe de configuração responsável pela configuração global do CORS (Cross-Origin Resource Sharing) na aplicação.
 * 
 * A classe {WebConfig} implementa a interface {WebMvcConfigurer} para personalizar a configuração do CORS. 
 * Isso permite que a aplicação controle quais origens externas podem acessar os recursos, quais métodos HTTP são permitidos, 
 * e quais cabeçalhos podem ser enviados nas requisições.
 * 
 * Essa configuração é importante para garantir que as requisições feitas por um frontend hospedado em um domínio diferente 
 * do backend sejam tratadas corretamente, sem problemas de segurança.
 * 
 * @see WebMvcConfigurer
 * @see CorsRegistry
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedHeaders(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT)
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name())
                .allowCredentials(true)
                .maxAge(3600L);
    }

}
