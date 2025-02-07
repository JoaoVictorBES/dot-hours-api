package br.com.system.dothours.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/*
 * Filtro responsável por interceptar requisições HTTP para validar o token JWT (JSON Web Token) 
 * e autenticar o usuário com base no token presente no cabeçalho "Authorization".
 * 
 * Este filtro verifica se a requisição contém um token JWT válido. Se o token for válido, 
 * ele é usado para autenticar o usuário no contexto de segurança da aplicação.
 * 
 * O filtro é executado uma vez por requisição, garantindo que o token seja validado 
 * e a autenticação seja configurada para a requisição.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private UserDetailsService userDetailsService;


	/*
     * Intercepta a requisição HTTP, verifica a presença e validade do token JWT, 
     * e realiza a autenticação do usuário caso o token seja válido.
     * 
     * O processo de autenticação ocorre da seguinte maneira:
     * 1. O cabeçalho "Authorization" é extraído da requisição.
     * 2. Se o token JWT estiver presente e for válido, o usuário será autenticado 
     *    e o contexto de segurança será atualizado com o objeto de autenticação.
     * 
     * Caso o token não seja válido ou não esteja presente, a requisição é 
     * encaminhada sem modificações.
     * 
     * @param request A requisição HTTP que será processada.
     * @param response A resposta HTTP a ser enviada de volta ao cliente.
     * @param filterChain A cadeia de filtros a ser seguida após o processamento deste filtro.
     * @throws ServletException Se ocorrer um erro ao processar a requisição.
     * @throws IOException Se ocorrer um erro de entrada/saída ao processar a requisição.
     */
	@Override
	protected void doFilterInternal(
		@NonNull HttpServletRequest request,
		@NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain
		) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request,response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			if(jwtService.isTokenValid(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
						);
				authToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						);
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}
