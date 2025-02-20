package br.com.system.dothours.config;



import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Filtro responsável por permitir o compartilhamento de recursos entre diferentes origens (CORS - Cross-Origin Resource Sharing).
 * Este filtro permite que o frontend, hospedado em "http://localhost:4200", faça requisições ao backend sem bloqueios de CORS.
 * 
 * O filtro configura os cabeçalhos CORS para todas as respostas HTTP, permitindo os métodos HTTP necessários e as origens específicas.
 * Também trata as requisições do tipo "OPTIONS", respondendo com um status HTTP 200 (OK), o que é necessário para a pré-verificação de CORS.
 * 
 * Este filtro é utilizado para garantir que o backend permita interações com o frontend de outras origens.
 */
@Component
public class SimpleCORSFilter implements Filter {

    /**
     * Método que processa a requisição e a resposta para aplicar as configurações de CORS.
     * 
     * Configura os cabeçalhos de resposta para permitir que o frontend hospedado em "http://localhost:4200" faça requisições 
     * ao backend com métodos HTTP específicos, como POST, PUT, GET, OPTIONS e DELETE.
     * Também permite que certos cabeçalhos, como "Authorization" e "Content-Type", sejam incluídos nas requisições.
     * Para requisições do tipo "OPTIONS" (pré-verificação de CORS), o filtro responde com um status HTTP 200 (OK).
     * 
     * @param req A requisição HTTP que será processada.
     * @param res A resposta HTTP a ser enviada de volta ao cliente.
     * @param chain A cadeia de filtros a ser seguida após o processamento deste filtro.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição ou resposta.
     * @throws ServletException Se ocorrer um erro ao processar a requisição ou resposta.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, Accept, Origin");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }



    /**
     * Método de inicialização do filtro. Não é utilizado neste caso, mas é necessário para implementar a interface Filter.
     * 
     * @param filterConfig Configurações do filtro (não utilizadas neste filtro).
     */
    @Override
    public void init(FilterConfig filterConfig) {}



      /**
     * Método de destruição do filtro. Não é utilizado neste caso, mas é necessário para implementar a interface Filter.
     */
    @Override
    public void destroy() {}

}
