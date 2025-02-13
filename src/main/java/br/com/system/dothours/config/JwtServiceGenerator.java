package br.com.system.dothours.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.system.dothours.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;



/*
 * Serviço responsável pela geração, validação e extração de informações de tokens JWT.
 * 
 * A classe {JwtServiceGenerator} fornece métodos para gerar um token JWT, validar a autenticidade do token e 
 * extrair informações contidas no token, como o nome de usuário e a data de expiração.
 * O token gerado inclui informações adicionais (extra claims) sobre o usuário, como o nome, id e role.
 * 
 * Além disso, a classe também contém a lógica para verificar a validade do token, incluindo a verificação da expiração.
 * A chave de assinatura utilizada é extraída a partir de uma chave secreta configurada.
 * 
 * @see Jwts
 * @see Claims
 */
@Service
public class JwtServiceGenerator {


     /**
     * Gera um token JWT para um usuário fornecido.
     * 
     * O token gerado inclui as informações do usuário (nome, id, role) como "claims" adicionais e define 
     * a data de expiração conforme configurado.
     * 
     * @param userDetails O usuário para o qual o token será gerado.
     * @return O token JWT gerado.
     */
    public String generateToken(Usuario userDetails) {
	
	  
        // Payload no qual eu posso buscar o parametros que eu quero//
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", userDetails.getUsername());
        extraClaims.put("id", userDetails.getId().toString());
        extraClaims.put("role", userDetails.getRole()); 
        
        
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(new Date().getTime() + 3600000 * JwtConfig.HORAS_EXPIRACAO_TOKEN))
                .signWith(getSigningKey(), JwtConfig.ALGORITMO_ASSINATURA)
                .compact();
    }
  


     /**
     * Extrai todas as declarações (claims) de um token JWT.
     * 
     * @param token O token JWT do qual as declarações serão extraídas.
     * @return As declarações extraídas do token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }



     /**
     * Valida a autenticidade de um token JWT.
     * Verifica se o nome de usuário extraído do token corresponde ao nome de usuário do usuário fornecido 
     * e se o token não está expirado.
     * 
     * @param token O token JWT a ser validado.
     * @param userDetails Os detalhes do usuário para comparar com o token.
     * @return {true} se o token for válido, {false} caso contrário.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }



    /**
     * Verifica se o token JWT expirou.
     * 
     * @param token O token JWT a ser verificado.
     * @return {true} se o token tiver expirado, {false} caso contrário.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }



    /*
     * Extrai a data de expiração de um token JWT.
     * 
     * @param token O token JWT do qual a data de expiração será extraída.
     * @return A data de expiração do token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }



    /**
     * Retorna a chave de assinatura utilizada para assinar os tokens JWT.
     * 
     * @return A chave de assinatura utilizada para assinar o token JWT.
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JwtConfig.SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    


    /**
     * Extrai o nome de usuário de um token JWT.
     * 
     * @param token O token JWT do qual o nome de usuário será extraído.
     * @return O nome de usuário extraído do token.
     */
    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }



    /**
     * Extrai uma determinada "claim" (informação) de um token JWT.
     * 
     * @param token O token JWT do qual a "claim" será extraída.
     * @param claimsResolver Função para resolver a claim desejada.
     * @param <T> O tipo da informação a ser extraída.
     * @return O valor da claim extraída do token.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }


}
